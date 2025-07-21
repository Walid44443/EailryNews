package com.walid44443.newsapp.data.remote.datasource

import com.walid44443.newsapp.data.remote.dto.NewsResponseDto

interface RemoteDataSource {
    suspend fun getNews(
        page: Int = 1,
        limit: Int = 20,
        search: String? = null,
        categories: String? = null
    ): NewsResponseDto
}