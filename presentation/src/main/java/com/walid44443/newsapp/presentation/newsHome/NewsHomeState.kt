package com.walid44443.newsapp.presentation.newsHome

import androidx.paging.PagingData
import com.walid44443.newsapp.domain.model.News
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

data class NewsHomeState(
    val isLoading: Boolean = false,
    val isRefreshing: Boolean = false,
    val news: Flow<PagingData<News>> = emptyFlow(),
    val offlineNews: List<News> = emptyList(),
    val error: String? = null,
    val networkAvailable: Boolean = true,
    val isOfflineMode: Boolean = false
)