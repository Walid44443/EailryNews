package com.walid44443.newsapp.domain.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.walid44443.newsapp.data.local.datasource.LocalDataSource
import com.walid44443.newsapp.data.remote.datasource.RemoteDataSource
import com.walid44443.newsapp.domain.mappers.toDomain
import com.walid44443.newsapp.domain.mappers.toEntityList
import com.walid44443.newsapp.domain.model.News
import com.walid44443.newsapp.domain.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlin.collections.map

class NewsRepositoryImpl(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : NewsRepository {

    override fun getNewsPaging(): Flow<PagingData<News>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { localDataSource.getAllNewsPaging() }
        ).flow.map { pagingData ->
            pagingData.map { it.toDomain() }
        }
    }

    override suspend fun getNewsById(id: String): Resource<News> {
        return try {
            val news = localDataSource.getNewsById(id)
            if (news != null) {
                Resource.Success(news.toDomain())
            } else {
                Resource.Error("News not found")
            }
        } catch (e: Exception) {
            Resource.Error(e.message ?: "Unknown error occurred")
        }
    }

    override suspend fun refreshNews(): Resource<Unit> {
        return try {
            val response = remoteDataSource.getNews()
            localDataSource.clearAllNews()
            localDataSource.insertNews(response.articles.toEntityList())
            Resource.Success(Unit)
        } catch (e: Exception) {
            Resource.Error(e.message ?: "Failed to refresh news")
        }
    }

    override fun getOfflineNews(): Flow<List<News>> {
        return localDataSource.getAllNews().map { entities ->
            entities.map { it.toDomain() }
        }
    }
}