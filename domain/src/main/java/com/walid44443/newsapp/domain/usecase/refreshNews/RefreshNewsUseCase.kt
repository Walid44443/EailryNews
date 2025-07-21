package com.walid44443.newsapp.domain.usecase.refreshNews

import com.walid44443.newsapp.domain.util.Resource

interface RefreshNewsUseCase {
    suspend operator fun invoke(): Resource<Unit>
}