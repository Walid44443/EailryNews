package com.walid44443.newsapp.presentation.newsHome.composables.components

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.runtime.Composable
import com.walid44443.newsapp.domain.model.News
import com.walid44443.newsapp.presentation.newsHome.NewsHomeIntent
import com.walid44443.newsapp.presentation.newsHome.NewsHomeState


@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun NewsHomeContent(
    state: NewsHomeState,
    onNewsClick: (News) -> Unit,
    onIntent: (NewsHomeIntent) -> Unit,
    animatedVisibilityScope: AnimatedVisibilityScope,
    sharedTransitionScope: SharedTransitionScope
) {
    when {
        state.isLoading && !state.isRefreshing -> {
            LoadingContent()
        }

        state.error != null && state.offlineNews.isEmpty() -> {
            ErrorContent(
                error = state.error,
                isOffline = !state.networkAvailable,
                onRetry = {
                    onIntent(
                        if (state.networkAvailable) NewsHomeIntent.LoadNews
                        else NewsHomeIntent.RetryConnection
                    )
                },
                onDismiss = { onIntent(NewsHomeIntent.ClearError) }
            )
        }

        state.isOfflineMode -> {
            OfflineNewsContent(
                newsList = state.offlineNews,
                onNewsClick = onNewsClick,
                onRetryConnection = { onIntent(NewsHomeIntent.RetryConnection) },
                animatedVisibilityScope = animatedVisibilityScope,
                sharedTransitionScope = sharedTransitionScope
            )
        }

        else -> {
            OnlineNewsContent(
                state = state,
                onNewsClick = onNewsClick,
                animatedVisibilityScope = animatedVisibilityScope,
                sharedTransitionScope = sharedTransitionScope
            )
        }
    }
}