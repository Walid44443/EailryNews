package com.walid44443.newsapp.domain.usecase.getOfflineNews

import com.walid44443.newsapp.domain.model.News
import kotlinx.coroutines.flow.Flow

interface GetOfflineNewsUseCase {
    operator fun invoke(): Flow<List<News>>
}