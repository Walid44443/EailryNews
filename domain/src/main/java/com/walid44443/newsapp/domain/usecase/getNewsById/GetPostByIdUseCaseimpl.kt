package com.walid44443.newsapp.domain.usecase.getNewsById

import com.walid44443.newsapp.domain.model.News
import com.walid44443.newsapp.domain.repository.NewsRepository
import com.walid44443.newsapp.domain.util.Resource

class GetNewsByIdUseCaseImpl(
    private val repository: NewsRepository
) : GetNewsByIdUseCase {
    override suspend operator fun invoke(id: String): Resource<News> {
        return repository.getNewsById(id)
    }
}