package com.walid44443.newsapp.presentation.newsDetails.composables.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.walid44443.newsapp.presentation.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsDetailsTopBar(
    title: String,
    onBackClick: () -> Unit,
    scrollBehavior: TopAppBarScrollBehavior? = null
) {
    TopAppBar(
        title = {
            Text(
                text = title.ifBlank { stringResource(R.string.news_details_title) },
                maxLines = 1
            )
        },
        navigationIcon = {
            IconButton(onClick = onBackClick) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = stringResource(R.string.back_button_content_description)
                )
            }
        },
        scrollBehavior = scrollBehavior
    )
}