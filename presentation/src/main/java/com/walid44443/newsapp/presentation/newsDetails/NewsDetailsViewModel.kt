package com.walid44443.newsapp.presentation.newsDetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.walid44443.newsapp.domain.usecase.getNewsById.GetNewsByIdUseCase
import com.walid44443.newsapp.domain.util.Resource
import com.walid44443.newsapp.presentation.navigation.newsDetails.NewsDetailsDestination
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class NewsDetailsViewModel(
    private val arguments: NewsDetailsDestination.NewsDetailsArgumentsData,
    private val getNewsByIdUseCase: GetNewsByIdUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(NewsDetailsState())
    val state: StateFlow<NewsDetailsState> = _state.asStateFlow()

    init {
        handleIntent(NewsDetailsIntent.LoadNewsDetails)
    }

    fun handleIntent(intent: NewsDetailsIntent) {
        when (intent) {
            is NewsDetailsIntent.LoadNewsDetails -> loadNewsDetails()
            is NewsDetailsIntent.RefreshNewsDetails -> loadNewsDetails()
            is NewsDetailsIntent.ClearError -> clearError()
        }
    }

    private fun loadNewsDetails() {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true, error = null)

            val newsId = arguments.newsId
            if (newsId.isNullOrBlank()) {
                _state.value = _state.value.copy(
                    isLoading = false,
                    error = "Invalid news ID",
                    newsDetails = null
                )
                return@launch
            }

            when (val result = getNewsByIdUseCase(newsId)) {
                is Resource.Success -> {
                    _state.value = _state.value.copy(
                        isLoading = false,
                        newsDetails = result.data,
                        error = null
                    )
                }
                is Resource.Error -> {
                    _state.value = _state.value.copy(
                        isLoading = false,
                        error = result.message ?: "Failed to load news details"
                    )
                }
                is Resource.Loading -> {
                    _state.value = _state.value.copy(isLoading = true)
                }
            }
        }
    }

    private fun clearError() {
        _state.value = _state.value.copy(error = null)
    }
}