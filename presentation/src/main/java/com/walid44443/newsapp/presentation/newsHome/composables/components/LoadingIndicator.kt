package com.walid44443.newsapp.presentation.newsHome.composables.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.walid44443.newsapp.presentation.theme.spacing

@Composable
fun LoadingIndicator(
    modifier: Modifier = Modifier
) {
    CircularProgressIndicator(
        modifier = modifier,
        color = MaterialTheme.colorScheme.primary
    )
}

@Composable
fun PaginationLoadingItem(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(MaterialTheme.spacing.medium),
        contentAlignment = Alignment.Center
    ) {
        LoadingIndicator()
    }
}