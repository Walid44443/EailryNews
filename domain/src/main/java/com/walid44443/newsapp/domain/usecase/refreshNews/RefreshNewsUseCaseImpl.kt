package com.walid44443.newsapp.domain.usecase.refreshNews


import com.walid44443.newsapp.domain.repository.NewsRepository
import com.walid44443.newsapp.domain.util.Resource

class RefreshNewsUseCaseImpl(
    private val repository: NewsRepository
) : RefreshNewsUseCase {
    override suspend operator fun invoke(): Resource<Unit> {
        return repository.refreshNews()
    }
}