package com.walid44443.newsapp.presentation.newsDetails

sealed class NewsDetailsIntent {
    data object LoadNewsDetails : NewsDetailsIntent()
    data object RefreshNewsDetails : NewsDetailsIntent()
    data object ClearError : NewsDetailsIntent()
}