package com.walid44443.newsapp

import android.app.Application
import com.walid44443.newsapp.data.di.databaseModule
import com.walid44443.newsapp.data.di.dataModule
import com.walid44443.newsapp.data.di.dataSourceModule
import com.walid44443.newsapp.data.di.networkModule
import com.walid44443.newsapp.domain.di.repositoryModule
import com.walid44443.newsapp.di.appModule
import com.walid44443.newsapp.domain.di.domainModule
import com.walid44443.newsapp.presentation.di.presentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class NewsApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@NewsApplication)
            modules(
                appModule,
                domainModule,
                dataModule,
                networkModule,
                databaseModule,
                dataSourceModule,
                repositoryModule,
                presentationModule
            )
        }
    }
}