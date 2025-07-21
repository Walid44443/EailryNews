package com.walid44443.newsapp.domain.di

import com.walid44443.newsapp.data.local.datasource.LocalDataSource
import com.walid44443.newsapp.data.remote.datasource.RemoteDataSource
import com.walid44443.newsapp.domain.repository.NewsRepository
import com.walid44443.newsapp.domain.repository.NewsRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {

    single<NewsRepository> {
        NewsRepositoryImpl(
            remoteDataSource = get<RemoteDataSource>(),
            localDataSource = get<LocalDataSource>()
        )
    }
}