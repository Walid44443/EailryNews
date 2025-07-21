package com.walid44443.newsapp.data.di

import androidx.room.Room
import com.walid44443.newsapp.data.local.database.NewsDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val databaseModule = module {

    single {
        Room.databaseBuilder(
            androidContext(),
            NewsDatabase::class.java,
            NewsDatabase.DATABASE_NAME
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    single { get<NewsDatabase>().newsDao() }
}