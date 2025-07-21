package com.walid44443.newsapp.presentation.newsHome.composables.components

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.walid44443.newsapp.domain.model.News
import com.walid44443.newsapp.presentation.newsHome.composables.NewsItem
import com.walid44443.newsapp.presentation.newsHome.composables.components.preview.MockData
import com.walid44443.newsapp.presentation.theme.NewsAppTheme
import com.walid44443.newsapp.presentation.theme.spacing

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun OfflineNewsContent(
    newsList: List<News>,
    onNewsClick: (News) -> Unit,
    onRetryConnection: () -> Unit,
    animatedVisibilityScope: AnimatedVisibilityScope,
    sharedTransitionScope: SharedTransitionScope
) {
    LazyColumn(
        contentPadding = PaddingValues(MaterialTheme.spacing.medium),
        verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.medium)
    ) {
        item {
            OfflineBanner(onRetryConnection = onRetryConnection)
        }

        items(
            items = newsList,
            key = { it.id }
        ) { news ->
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



@OptIn(ExperimentalSharedTransitionApi::class)
@Preview(showBackground = true)
@Composable
private fun OfflineNewsContentPreview() {
    NewsAppTheme {
        Surface {
            SharedTransitionLayout {
                AnimatedContent(
                    targetState = true,
                    label = "OfflineNewsContentPreview"
                ) { _ ->
                    OfflineNewsContent(
                        newsList = MockData.sampleNewsList,
                        onNewsClick = { },
                        onRetryConnection = { },
                        animatedVisibilityScope = this,
                        sharedTransitionScope = this@SharedTransitionLayout
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalSharedTransitionApi::class)
@Preview(showBackground = true)
@Composable
private fun OfflineNewsContentEmptyPreview() {
    NewsAppTheme {
        Surface {
            SharedTransitionLayout {
                AnimatedContent(
                    targetState = true,
                    label = "OfflineNewsContentEmptyPreview"
                ) { _ ->
                    OfflineNewsContent(
                        newsList = emptyList(),
                        onNewsClick = { },
                        onRetryConnection = { },
                        animatedVisibilityScope = this,
                        sharedTransitionScope = this@SharedTransitionLayout
                    )
                }
            }
        }
    }
}