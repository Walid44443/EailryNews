package com.walid44443.newsapp.presentation.newsHome.composables.components

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.walid44443.newsapp.domain.model.News
import com.walid44443.newsapp.presentation.R
import com.walid44443.newsapp.presentation.theme.spacing
import com.walid44443.newsapp.presentation.utils.DateUtils

@OptIn(ExperimentalMaterial3Api::class, ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.NewsItemCard(
    modifier: Modifier = Modifier,
    news: News,
    animatedVisibilityScope: AnimatedVisibilityScope,
    onClick: () -> Unit,
) {
    Card(
        onClick = onClick,
        modifier = modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        )
    ) {
        Column {
            // Shared Image
            news.imageUrl?.let { imageUrl ->
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(imageUrl)
                        .crossfade(true)
                        .build(),
                    contentDescription = stringResource(R.string.content_description_news_image),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .clip(RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp))
                        .sharedElement(
                            sharedContentState = rememberSharedContentState(key = "image-${news.id}"),
                            animatedVisibilityScope = animatedVisibilityScope
                        ),
                    contentScale = ContentScale.Crop,
                    placeholder = painterResource(R.drawable.ic_placeholder_image),
                    error = painterResource(R.drawable.ic_placeholder_image)
                )
            }

            // Content
            Column(
                modifier = Modifier.padding(MaterialTheme.spacing.medium)
            ) {
                // Shared Title
                Text(
                    text = news.title,
                    style = MaterialTheme.typography.headlineSmall,
                    color = MaterialTheme.colorScheme.onSurface,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.sharedElement(
                        sharedContentState = rememberSharedContentState(key = "title-${news.id}"),
                        animatedVisibilityScope = animatedVisibilityScope
                    )
                )

                Spacer(modifier = Modifier.height(MaterialTheme.spacing.small))

                // Description
                news.description?.let { description ->
                    Text(
                        text = description,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        maxLines = 3,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.sharedElement(
                            sharedContentState = rememberSharedContentState(key = "description-${news.id}"),
                            animatedVisibilityScope = animatedVisibilityScope
                        )
                    )

                    Spacer(modifier = Modifier.height(MaterialTheme.spacing.small))
                }

                // Footer with source and date
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Source
                    Text(
                        text = news.source,
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.primary,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.weight(1f)
                    )

                    Spacer(modifier = Modifier.width(MaterialTheme.spacing.small))

                    // Published date
                    Text(
                        text = DateUtils.formatTimeAgo(news.publishedAt),
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }
    }
}