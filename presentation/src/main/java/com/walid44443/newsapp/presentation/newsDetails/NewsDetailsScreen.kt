package com.walid44443.newsapp.presentation.newsDetails

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.walid44443.newsapp.presentation.R
import com.walid44443.newsapp.presentation.navigation.newsDetails.NewsDetailsDestination
import com.walid44443.newsapp.presentation.newsDetails.composables.components.NewsDetailsTopBar
import com.walid44443.newsapp.presentation.theme.spacing

@OptIn(ExperimentalMaterial3Api::class, ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.NewsDetailsScreen(
    arguments: NewsDetailsDestination.NewsDetailsArgumentsData,
    animatedVisibilityScope: AnimatedVisibilityScope,
    onBackClick: () -> Unit
) {
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding()
    ) {
        // Top Bar
        NewsDetailsTopBar(
            title = arguments.newsTitle.orEmpty(),
            onBackClick = onBackClick
        )

        // Content
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
        ) {
            // Shared Image
            if (arguments.newsImageUrl?.isNotEmpty() == true) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(arguments.newsImageUrl)
                        .crossfade(true)
                        .build(),
                    contentDescription = stringResource(R.string.content_description_news_image),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(250.dp)
                        .sharedElement(
                            sharedContentState = rememberSharedContentState(key = "image-${arguments.newsId}"),
                            animatedVisibilityScope = animatedVisibilityScope
                        ),
                    contentScale = ContentScale.Crop,
                    placeholder = painterResource(R.drawable.ic_placeholder_image),
                    error = painterResource(R.drawable.ic_placeholder_image)
                )
            }

            Column(
                modifier = Modifier.padding(MaterialTheme.spacing.medium)
            ) {
                // Shared Title
                Text(
                    text = arguments.newsTitle.orEmpty(),
                    style = MaterialTheme.typography.headlineMedium,
                    color = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.sharedElement(
                        sharedContentState = rememberSharedContentState(key = "title-${arguments.newsId}"),
                        animatedVisibilityScope = animatedVisibilityScope
                    )
                )

                Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))

                // Shared Content
                Text(
                    text = arguments.newsContent.orEmpty(),
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.sharedElement(
                        sharedContentState = rememberSharedContentState(key = "description-${arguments.newsId}"),
                        animatedVisibilityScope = animatedVisibilityScope
                    )
                )
            }
        }
    }
}