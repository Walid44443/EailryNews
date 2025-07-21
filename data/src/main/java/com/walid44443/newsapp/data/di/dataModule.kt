package com.walid44443.newsapp.data.di

import org.koin.dsl.module

val dataModule = module {
    includes(
        networkModule,
        databaseModule,
        dataSourceModule
    )
}