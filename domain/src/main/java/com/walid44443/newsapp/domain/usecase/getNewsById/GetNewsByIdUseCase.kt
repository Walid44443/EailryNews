package com.walid44443.newsapp.domain.usecase.getNewsById

import com.walid44443.newsapp.domain.model.News
import com.walid44443.newsapp.domain.util.Resource

interface GetNewsByIdUseCase {
    suspend operator fun invoke(id: String): Resource<News>
}