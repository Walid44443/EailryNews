package com.walid44443.newsapp.presentation.utils.networkConnectivity

import kotlinx.coroutines.flow.Flow

interface NetworkConnectivityObserver {
    fun observe(): Flow<Boolean>
}