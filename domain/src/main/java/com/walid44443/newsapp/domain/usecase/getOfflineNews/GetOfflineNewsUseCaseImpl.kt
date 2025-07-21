package com.walid44443.newsapp.domain.usecase.getOfflineNews

import com.walid44443.newsapp.domain.model.News
import com.walid44443.newsapp.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class GetOfflineNewsUseCaseImpl(
    private val repository: NewsRepository
) : GetOfflineNewsUseCase {
    override operator fun invoke(): Flow<List<News>> {
        return repository.getOfflineNews()
    }
}