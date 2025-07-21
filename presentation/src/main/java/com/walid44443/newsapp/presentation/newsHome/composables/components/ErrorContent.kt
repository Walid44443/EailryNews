package com.walid44443.newsapp.presentation.newsHome.composables.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.walid44443.newsapp.presentation.R
import com.walid44443.newsapp.presentation.theme.NewsAppTheme
import com.walid44443.newsapp.presentation.theme.spacing

@Composable
fun ErrorContent(
    error: String,
    isOffline: Boolean,
    onRetry: () -> Unit,
    onDismiss: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(MaterialTheme.spacing.medium),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            imageVector = if (isOffline) Icons.Default.Build else Icons.Default.Refresh,
            contentDescription = null,
            modifier = Modifier.size(48.dp),
            tint = MaterialTheme.colorScheme.onSurfaceVariant
        )

        Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))

        Text(
            text = if (isOffline) {
                stringResource(R.string.no_internet_connection)
            } else {
                stringResource(R.string.something_went_wrong)
            },
            style = MaterialTheme.typography.headlineSmall,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(MaterialTheme.spacing.small))

        Text(
            text = error,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))

        Row(
            horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.small)
        ) {
            Button(onClick = onRetry) {
                Text(
                    if (isOffline) {
                        stringResource(R.string.retry_connection)
                    } else {
                        stringResource(R.string.retry)
                    }
                )
            }

            OutlinedButton(onClick = onDismiss) {
                Text(stringResource(R.string.dismiss))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ErrorContentPreview() {
    NewsAppTheme {
        Surface {
            ErrorContent(
                error = "Unable to load news articles. Please check your internet connection.",
                isOffline = false,
                onRetry = { },
                onDismiss = { }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ErrorContentOfflinePreview() {
    NewsAppTheme {
        Surface {
            ErrorContent(
                error = "No internet connection available.",
                isOffline = true,
                onRetry = { },
                onDismiss = { }
            )
        }
    }
}

@Preview(showBackground = true, uiMode = android.content.res.Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun ErrorContentDarkPreview() {
    NewsAppTheme {
        Surface {
            ErrorContent(
                error = "Something went wrong while loading the news.",
                isOffline = false,
                onRetry = { },
                onDismiss = { }
            )
        }
    }
}