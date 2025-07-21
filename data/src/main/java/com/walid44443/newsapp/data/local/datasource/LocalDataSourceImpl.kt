package com.walid44443.newsapp.data.local.datasource

import androidx.paging.PagingSource
import com.walid44443.newsapp.data.local.dao.NewsDao
import com.walid44443.newsapp.data.local.entity.NewsEntity
import kotlinx.coroutines.flow.Flow

class LocalDataSourceImpl(
    private val newsDao: NewsDao
) : LocalDataSource {

    override fun getAllNewsPaging(): PagingSource<Int, NewsEntity> = newsDao.getAllNewsPaging()

    override fun getAllNews(): Flow<List<NewsEntity>> = newsDao.getAllNews()

    override suspend fun getNewsById(uuid: String): NewsEntity? = newsDao.getNewsById(uuid)

    override suspend fun insertNews(news: List<NewsEntity>) = newsDao.insertNews(news)

    override suspend fun insertNews(news: NewsEntity) = newsDao.insertNews(news)

    override suspend fun clearAllNews() = newsDao.clearAllNews()

    override suspend fun getNewsCount(): Int = newsDao.getNewsCount()
}