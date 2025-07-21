package com.walid44443.newsapp.presentation.newsHome.composables.components

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import com.walid44443.newsapp.domain.model.News
import com.walid44443.newsapp.presentation.newsHome.NewsHomeState
import com.walid44443.newsapp.presentation.newsHome.composables.NewsItem
import com.walid44443.newsapp.presentation.theme.spacing

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun OnlineNewsContent(
    state: NewsHomeState,
    onNewsClick: (News) -> Unit,
    animatedVisibilityScope: AnimatedVisibilityScope,
    sharedTransitionScope: SharedTransitionScope
) {
    val lazyPagingItems = state.news.collectAsLazyPagingItems()

    LazyColumn(
        contentPadding = PaddingValues(MaterialTheme.spacing.medium),
        verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.medium)
    ) {
        items(
            count = lazyPagingItems.itemCount,
            key = lazyPagingItems.itemKey { it.id },
            contentType = lazyPagingItems.itemContentType { "News" }
        ) { index ->
            lazyPagingItems[index]?.let { news ->
                with(sharedTransitionScope) {
                    NewsItem(
                        news = news,
                        onNewsClick = { onNewsClick(news) },
                        animatedVisibilityScope = animatedVisibilityScope,
                    )
                }
            }
        }
    }
}