package com.walid44443.newsapp.presentation.newsHome

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.walid44443.newsapp.domain.usecase.NewsUseCases
import com.walid44443.newsapp.presentation.utils.networkConnectivity.NetworkConnectivityObserver
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.launch

class NewsViewModel(
    private val newsUseCases: NewsUseCases,
    private val networkConnectivityObserver: NetworkConnectivityObserver
) : ViewModel() {

    private val _state = MutableStateFlow(NewsHomeState())
    val state: StateFlow<NewsHomeState> = _state.asStateFlow()

    init {
        // Initialize news flow immediately without waiting for network
        initializeNews()
        observeNetworkConnectivity()
    }

    private fun initializeNews() {
        viewModelScope.launch {
            try {
                _state.value = _state.value.copy(isLoading = true)

                // Always try to load News first
                val newsFlow = newsUseCases.getNewsUseCase()
                    .cachedIn(viewModelScope)

                _state.value = _state.value.copy(
                    isLoading = false,
                    news = newsFlow,
                    error = null
                )

            } catch (e: Exception) {
                _state.value = _state.value.copy(
                    isLoading = false,
                    error = e.message ?: "Failed to load news"
                )

                // Fallback to offline data if available
                loadOfflineNews()
            }
        }
    }

    fun handleIntent(intent: NewsHomeIntent) {
        when (intent) {
            is NewsHomeIntent.LoadNews -> loadNews()
            is NewsHomeIntent.RefreshNews -> refreshNews()
            is NewsHomeIntent.LoadOfflineNews -> loadOfflineNews()
            is NewsHomeIntent.RetryConnection -> retryConnection()
            is NewsHomeIntent.ClearError -> clearError()
            is NewsHomeIntent.NetworkStatusChanged -> updateNetworkStatus(intent.isAvailable)
        }
    }

    private fun observeNetworkConnectivity() {
        viewModelScope.launch {
            networkConnectivityObserver.observe().collect { isConnected ->
                _state.value = _state.value.copy(networkAvailable = isConnected)

                if (isConnected && _state.value.isOfflineMode) {
                    // Network restored, refresh data
                    loadNews()
                } else if (!isConnected && !_state.value.isOfflineMode) {
                    // Network lost, switch to offline mode
                    _state.value = _state.value.copy(isOfflineMode = true)
                }
            }
        }
    }

    private fun loadNews() {
        viewModelScope.launch {
            try {
                _state.value = _state.value.copy(
                    isLoading = true,
                    error = null,
                    isOfflineMode = false
                )

                val newsFlow = newsUseCases.getNewsUseCase()
                    .cachedIn(viewModelScope)

                _state.value = _state.value.copy(
                    isLoading = false,
                    news = newsFlow,
                    isOfflineMode = false,
                    offlineNews = emptyList()
                )

            } catch (e: Exception) {
                _state.value = _state.value.copy(
                    isLoading = false,
                    error = e.message ?: "Failed to load news"
                )
                loadOfflineNews()
            }
        }
    }

    private fun refreshNews() {
        viewModelScope.launch {
            try {
                _state.value = _state.value.copy(
                    isRefreshing = true,
                    error = null
                )

                if (!_state.value.networkAvailable) {
                    _state.value = _state.value.copy(
                        isRefreshing = false,
                        error = "No internet connection"
                    )
                    return@launch
                }

                // Clear cache and reload
                newsUseCases.refreshNewsUseCase()

                val newsFlow = newsUseCases.getNewsUseCase()
                    .cachedIn(viewModelScope)

                _state.value = _state.value.copy(
                    isRefreshing = false,
                    news = newsFlow,
                    isOfflineMode = false
                )

            } catch (e: Exception) {
                _state.value = _state.value.copy(
                    isRefreshing = false,
                    error = e.message ?: "Failed to refresh news"
                )
            }
        }
    }

    private fun loadOfflineNews() {
        viewModelScope.launch {
            try {
                newsUseCases.getOfflineNewsUseCase().collect { offlineNews ->
                    _state.value = _state.value.copy(
                        isLoading = false,
                        offlineNews = offlineNews,
                        isOfflineMode = true,
                        news = emptyFlow()
                    )
                }
            } catch (e: Exception) {
                _state.value = _state.value.copy(
                    isLoading = false,
                    error = e.message ?: "Failed to load offline news",
                    isOfflineMode = true
                )
            }
        }
    }

    private fun retryConnection() {
        if (_state.value.networkAvailable) {
            loadNews()
        } else {
            _state.value = _state.value.copy(
                error = "No internet connection available"
            )
        }
    }

    private fun clearError() {
        _state.value = _state.value.copy(error = null)
    }

    private fun updateNetworkStatus(isAvailable: Boolean) {
        _state.value = _state.value.copy(networkAvailable = isAvailable)
    }
}