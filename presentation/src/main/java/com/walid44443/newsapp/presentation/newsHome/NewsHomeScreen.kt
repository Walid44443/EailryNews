package com.walid44443.newsapp.presentation.newsHome

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.walid44443.newsapp.domain.model.News
import com.walid44443.newsapp.presentation.newsHome.composables.components.NewsHomeContent
import com.walid44443.newsapp.presentation.newsHome.composables.components.NewsHomeTopBar
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class, ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.NewsHomeScreen(
    viewModel: NewsViewModel = koinViewModel(),
    animatedVisibilityScope: AnimatedVisibilityScope,
    onNavigateToDetail: (News) -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val pullToRefreshState = rememberPullToRefreshState()

    LaunchedEffect(state.networkAvailable) {
        viewModel.handleIntent(
            if (state.networkAvailable) {
                NewsHomeIntent.RefreshNews
            } else {
                NewsHomeIntent.RetryConnection
            }
        )
    }

    Scaffold(
        topBar = {
            NewsHomeTopBar(
                isOffline = !state.networkAvailable,
                onRefreshClick = {
                    viewModel.handleIntent(
                        if (state.networkAvailable) NewsHomeIntent.RefreshNews
                        else NewsHomeIntent.RetryConnection
                    )
                }
            )
        }
    ) { paddingValues ->
        PullToRefreshBox(
            isRefreshing = state.isRefreshing,
            onRefresh = {
                viewModel.handleIntent(
                    if (state.networkAvailable) NewsHomeIntent.RefreshNews
                    else NewsHomeIntent.RetryConnection
                )
            },
            state = pullToRefreshState,
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            NewsHomeContent(
                state = state,
                onNewsClick = onNavigateToDetail,
                onIntent = viewModel::handleIntent,
                animatedVisibilityScope = animatedVisibilityScope,
                sharedTransitionScope = this@NewsHomeScreen
            )
        }
    }
}
