package com.walid44443.newsapp.domain.usecase.getNews

import androidx.paging.PagingData
import com.walid44443.newsapp.domain.model.News
import kotlinx.coroutines.flow.Flow

interface GetNewsUseCase{
    operator fun invoke(): Flow<PagingData<News>>
}