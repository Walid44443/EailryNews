package com.walid44443.newsapp.di


import com.walid44443.newsapp.BuildConfig
import com.walid44443.newsapp.data.di.NetworkData
import org.koin.dsl.module

val appModule = module {

    single<String>(qualifier = org.koin.core.qualifier.named(NetworkData.ApiKey.value)) {
        BuildConfig.NEWS_API_KEY
    }

    single<String>(qualifier = org.koin.core.qualifier.named(NetworkData.BaseUrl.value)) {
        "https://api.thenewsapi.com/v1/news/"
    }
}