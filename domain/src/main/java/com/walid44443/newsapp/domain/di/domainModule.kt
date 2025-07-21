package com.walid44443.newsapp.domain.di

import com.walid44443.newsapp.domain.usecase.NewsUseCases
import com.walid44443.newsapp.domain.usecase.getOfflineNews.GetOfflineNewsUseCase
import com.walid44443.newsapp.domain.usecase.getOfflineNews.GetOfflineNewsUseCaseImpl
import com.walid44443.newsapp.domain.usecase.getNewsById.GetNewsByIdUseCase
import com.walid44443.newsapp.domain.usecase.getNewsById.GetNewsByIdUseCaseImpl
import com.walid44443.newsapp.domain.usecase.getNews.GetNewsUseCase
import com.walid44443.newsapp.domain.usecase.getNews.GetNewsUseCaseImpl
import com.walid44443.newsapp.domain.usecase.refreshNews.RefreshNewsUseCase
import com.walid44443.newsapp.domain.usecase.refreshNews.RefreshNewsUseCaseImpl
import org.koin.dsl.module

val domainModule = module {

    // Use Case Implementations
    single<GetNewsUseCase> {
        GetNewsUseCaseImpl(repository = get())
    }

    single<GetNewsByIdUseCase> {
        GetNewsByIdUseCaseImpl(repository = get())
    }

    single<RefreshNewsUseCase> {
        RefreshNewsUseCaseImpl(repository = get())
    }

    single<GetOfflineNewsUseCase> {
        GetOfflineNewsUseCaseImpl(repository = get())
    }

    // Use Cases Container
    single {
        NewsUseCases(
            getNewsUseCase = get(),
            getNewsByIdUseCase = get(),
            refreshNewsUseCase = get(),
            getOfflineNewsUseCase = get()
        )
    }
}