package com.walid44443.newsapp.domain.repository

import androidx.paging.PagingData
import com.walid44443.newsapp.domain.model.News
import com.walid44443.newsapp.domain.util.Resource
import kotlinx.coroutines.flow.Flow

interface NewsRepository {
    fun getNewsPaging(): Flow<PagingData<News>>
    suspend fun getNewsById(id: String): Resource<News>
    suspend fun refreshNews(): Resource<Unit>
    fun getOfflineNews(): Flow<List<News>>
}