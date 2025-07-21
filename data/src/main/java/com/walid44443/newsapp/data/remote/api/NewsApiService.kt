package com.walid44443.newsapp.data.remote.api

import com.walid44443.newsapp.data.remote.dto.NewsResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiService {

    @GET("all")
    suspend fun getNews(
        @Query("api_token") apiToken: String,
        @Query("language") language: String = "en",
        @Query("limit") limit: Int = 20,
        @Query("page") page: Int = 1,
        @Query("search") search: String? = null,
        @Query("categories") categories: String? = null
    ): NewsResponseDto

    companion object {
        const val API_KEY = "rcXWyiC4Nm2srrNN65btHaM0iKtbDGD3NDahPRGa"
    }
}