package com.walid44443.newsapp.presentation.newsHome.composables.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.walid44443.newsapp.presentation.R
import com.walid44443.newsapp.presentation.theme.NewsAppTheme
import com.walid44443.newsapp.presentation.theme.spacing

@Composable
fun OfflineBanner(
    onRetryConnection: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        )
    ) {
        Row(
            modifier = Modifier.padding(MaterialTheme.spacing.medium),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.Build,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Spacer(modifier = Modifier.width(MaterialTheme.spacing.small))
            Text(
                text = stringResource(R.string.offline_mode_banner),
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.weight(1f)
            )
            TextButton(onClick = onRetryConnection) {
                Text(stringResource(R.string.retry))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun OfflineBannerPreview() {
    NewsAppTheme {
        Surface {
            OfflineBanner(
                onRetryConnection = { }
            )
        }
    }
}

@Preview(showBackground = true, uiMode = android.content.res.Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun OfflineBannerDarkPreview() {
    NewsAppTheme {
        Surface {
            OfflineBanner(
                onRetryConnection = { }
            )
        }
    }
}