package com.walid44443.newsapp.presentation.newsDetails.composables

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.walid44443.newsapp.presentation.R
import com.walid44443.newsapp.domain.model.News
import com.walid44443.newsapp.presentation.newsDetails.NewsDetailsIntent
import com.walid44443.newsapp.presentation.newsDetails.NewsDetailsState
import com.walid44443.newsapp.presentation.theme.spacing

@Composable
fun NewsDetailsContent(
    modifier: Modifier = Modifier,
    state: NewsDetailsState,
    onIntent: (NewsDetailsIntent) -> Unit
) {
    Box(modifier = modifier) {
        when {
            state.isLoading -> {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            }

            state.error != null && state.newsDetails == null -> {
                ErrorContent(
                    error = state.error,
                    onRetry = { onIntent(NewsDetailsIntent.LoadNewsDetails) }
                )
            }

            state.newsDetails != null -> {
                NewsDetailsSuccessContent(
                    news = state.newsDetails,
                    isRefreshing = state.isRefreshing,
                    onRefresh = { onIntent(NewsDetailsIntent.RefreshNewsDetails) }
                )
            }

            else -> {
                EmptyContent()
            }
        }
    }
}

@Composable
private fun NewsDetailsSuccessContent(
    news: News,
    isRefreshing: Boolean,
    onRefresh: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        // News Image
        news.imageUrl?.let { imageUrl ->
            AsyncImage(
                model = imageUrl,
                contentDescription = news.title,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))
        }

        // News Title
        Text(
            text = news.title,
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = MaterialTheme.spacing.small)
        )

        // News Source and Date
        news.source.let { source ->
            Text(
                text = source,
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.padding(bottom = MaterialTheme.spacing.small)
            )
        }

        news.publishedAt.let { publishedAt ->
            Text(
                text = publishedAt,
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.padding(bottom = MaterialTheme.spacing.medium)
            )
        }

        // News Content
        news.description?.let { description ->
            Text(
                text = description,
                style = MaterialTheme.typography.bodyLarge,
                lineHeight = MaterialTheme.typography.bodyLarge.lineHeight
            )
        } ?: run {
            Text(
                text = stringResource(R.string.news_details_no_content),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Composable
private fun ErrorContent(
    error: String,
    onRetry: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(R.string.news_details_error),
            style = MaterialTheme.typography.headlineSmall,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(MaterialTheme.spacing.small))
        Text(
            text = error,
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))
        Button(onClick = onRetry) {
            Text(stringResource(R.string.news_details_retry))
        }
    }
}

@Composable
private fun EmptyContent() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = stringResource(R.string.news_details_no_content),
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center
        )
    }
}