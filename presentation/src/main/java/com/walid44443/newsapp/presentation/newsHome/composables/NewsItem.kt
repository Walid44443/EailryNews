package com.walid44443.newsapp.presentation.newsHome.composables

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.walid44443.newsapp.domain.model.News
import com.walid44443.newsapp.presentation.newsHome.composables.components.preview.MockData
import com.walid44443.newsapp.presentation.theme.NewsAppTheme
import com.walid44443.newsapp.presentation.theme.spacing
import com.walid44443.newsapp.presentation.utils.DateUtils

@OptIn(ExperimentalMaterial3Api::class, ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.NewsItem(
    modifier: Modifier = Modifier,
    news: News,
    onNewsClick: () -> Unit,
    animatedVisibilityScope: AnimatedVisibilityScope,
) {
    Card(
        onClick = onNewsClick,
        modifier = modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        )
    ) {
        Column(
            modifier = Modifier.padding(MaterialTheme.spacing.medium)
        ) {
            // News Image with shared transition
            news.imageUrl?.let { imageUrl ->
                AsyncImage(
                    model = imageUrl,
                    contentDescription = news.title,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(180.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .sharedElement(
                            sharedContentState = rememberSharedContentState(key = "image-${news.id}"),
                            animatedVisibilityScope = animatedVisibilityScope
                        ),
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier = Modifier.height(MaterialTheme.spacing.small))
            }

            // News Title with shared transition
            Text(
                text = news.title,
                style = MaterialTheme.typography.titleMedium,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .padding(bottom = MaterialTheme.spacing.extraSmall)
                    .sharedElement(
                        sharedContentState = rememberSharedContentState(key = "title-${news.id}"),
                        animatedVisibilityScope = animatedVisibilityScope
                    )
            )

            // News Description
            news.description?.let { description ->
                Text(
                    text = description,
                    style = MaterialTheme.typography.bodyMedium,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.padding(bottom = MaterialTheme.spacing.small)
                )
            }

            // News Metadata
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                news.source.let { source ->
                    Text(
                        text = source,
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.weight(1f)
                    )
                }

                news.publishedAt.let { publishedAt ->
                    Text(
                        text = DateUtils.formatTimeAgo(publishedAt),
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }
    }
}


@OptIn(ExperimentalSharedTransitionApi::class)
@Preview(showBackground = true)
@Composable
private fun NewsItemPreview() {
    NewsAppTheme {
        Surface {
            SharedTransitionLayout {
                AnimatedVisibility(true) {
                    NewsItem(
                        news = MockData.sampleNews,
                        onNewsClick = { },
                        animatedVisibilityScope = this
                    )
                }
            }
        }
    }
}


@OptIn(ExperimentalSharedTransitionApi::class)
@Preview(showBackground = true)
@Composable
private fun NewsItemNoImagePreview() {
    NewsAppTheme {
        Surface {
            SharedTransitionLayout {
                AnimatedVisibility(true) {
                    NewsItem(
                        news = MockData.sampleNewsWithoutImage,
                        onNewsClick = { },
                        animatedVisibilityScope = this
                    )
                }
            }
        }
    }
}