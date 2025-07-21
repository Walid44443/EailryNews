package com.walid44443.newsapp.domain.usecase.getNews

import androidx.paging.PagingData
import com.walid44443.newsapp.domain.model.News
import com.walid44443.newsapp.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class GetNewsUseCaseImpl(
    private val repository: NewsRepository
) : GetNewsUseCase {
    override operator fun invoke(): Flow<PagingData<News>> {
        return repository.getNewsPaging()
    }
}