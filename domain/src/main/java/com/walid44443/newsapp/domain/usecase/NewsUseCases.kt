package com.walid44443.newsapp.domain.usecase

import com.walid44443.newsapp.domain.usecase.getOfflineNews.GetOfflineNewsUseCase
import com.walid44443.newsapp.domain.usecase.getNewsById.GetNewsByIdUseCase
import com.walid44443.newsapp.domain.usecase.getNews.GetNewsUseCase
import com.walid44443.newsapp.domain.usecase.refreshNews.RefreshNewsUseCase

data class NewsUseCases(
    val getNewsUseCase: GetNewsUseCase,
    val getNewsByIdUseCase: GetNewsByIdUseCase,
    val refreshNewsUseCase: RefreshNewsUseCase,
    val getOfflineNewsUseCase: GetOfflineNewsUseCase
)