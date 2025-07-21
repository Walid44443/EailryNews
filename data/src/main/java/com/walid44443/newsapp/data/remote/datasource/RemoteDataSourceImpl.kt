package com.walid44443.newsapp.data.remote.datasource

import com.walid44443.newsapp.data.remote.api.NewsApiService
import com.walid44443.newsapp.data.remote.dto.NewsResponseDto

class RemoteDataSourceImpl(
    private val apiService: NewsApiService,
    private val apiKey: String
) : RemoteDataSource {

    override suspend fun getNews(
        page: Int,
        limit: Int,
        search: String?,
        categories: String?
    ): NewsResponseDto {
        return apiService.getNews(
            apiToken = apiKey,
            page = page,
            limit = limit,
            search = search,
            categories = categories
        )
    }
}