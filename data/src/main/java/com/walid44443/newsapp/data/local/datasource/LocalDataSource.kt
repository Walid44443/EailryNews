package com.walid44443.newsapp.data.local.datasource

import androidx.paging.PagingSource
import com.walid44443.newsapp.data.local.entity.NewsEntity
import kotlinx.coroutines.flow.Flow

interface LocalDataSource {
    fun getAllNewsPaging(): PagingSource<Int, NewsEntity>
    fun getAllNews(): Flow<List<NewsEntity>>
    suspend fun getNewsById(uuid: String): NewsEntity?
    suspend fun insertNews(news: List<NewsEntity>)
    suspend fun insertNews(news: NewsEntity)
    suspend fun clearAllNews()
    suspend fun getNewsCount(): Int
}