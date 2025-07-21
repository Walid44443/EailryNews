package com.walid44443.newsapp.data.di

import com.walid44443.newsapp.data.local.datasource.LocalDataSource
import com.walid44443.newsapp.data.local.datasource.LocalDataSourceImpl
import com.walid44443.newsapp.data.remote.datasource.RemoteDataSource
import com.walid44443.newsapp.data.remote.datasource.RemoteDataSourceImpl
import org.koin.core.qualifier.named
import org.koin.dsl.module

val dataSourceModule = module {

    single<RemoteDataSource> {
        RemoteDataSourceImpl(
            apiService = get(),
            apiKey = get<String>(named(NetworkData.ApiKey.value))
        )
    }

    single<LocalDataSource> {
        LocalDataSourceImpl(newsDao = get())
    }
}