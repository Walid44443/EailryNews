package com.walid44443.newsapp.presentation.newsDetails

import com.walid44443.newsapp.domain.model.News

data class NewsDetailsState(
    val isLoading: Boolean = false,
    val newsDetails: News? = null,
    val error: String? = null,
    val isRefreshing: Boolean = false
)
