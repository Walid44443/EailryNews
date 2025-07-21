package com.walid44443.newsapp.presentation.newsHome

sealed class NewsHomeIntent {
    data object LoadNews : NewsHomeIntent()
    data object RefreshNews : NewsHomeIntent()
    data object LoadOfflineNews : NewsHomeIntent()
    data object RetryConnection : NewsHomeIntent()
    data object ClearError : NewsHomeIntent()
    data class NetworkStatusChanged(val isAvailable: Boolean) : NewsHomeIntent()
}
