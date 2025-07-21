package com.walid44443.newsapp.presentation.newsHome.composables.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.walid44443.newsapp.presentation.R
import com.walid44443.newsapp.presentation.theme.spacing

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsHomeTopBar(
    isOffline: Boolean,
    onRefreshClick: () -> Unit
) {
    TopAppBar(
        title = { Text(stringResource(R.string.app_name)) },
        actions = {
            if (isOffline) {
                Icon(
                    imageVector = Icons.Default.Build,
                    contentDescription = stringResource(R.string.offline_indicator),
                    tint = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Spacer(modifier = Modifier.width(MaterialTheme.spacing.small))
            }
            IconButton(onClick = onRefreshClick) {
                Icon(
                    imageVector = Icons.Default.Refresh,
                    contentDescription = stringResource(R.string.refresh)
                )
            }
        }
    )
}