package com.walid44443.newsapp.data.local.database


import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.walid44443.newsapp.data.local.converters.Converters
import com.walid44443.newsapp.data.local.dao.NewsDao
import com.walid44443.newsapp.data.local.entity.NewsEntity

@Database(
    entities = [NewsEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class NewsDatabase : RoomDatabase() {

    abstract fun newsDao(): NewsDao

    companion object {
        const val DATABASE_NAME = "news_database"
    }
}