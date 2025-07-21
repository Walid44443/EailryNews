package com.walid44443.newsapp.data.remote.dto

import com.google.gson.annotations.SerializedName

data class ArticleDto(
    @SerializedName("uuid")
    val uuid: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("description")
    val description: String?,
    @SerializedName("keywords")
    val keywords: String?,
    @SerializedName("snippet")
    val snippet: String?,
    @SerializedName("url")
    val url: String,
    @SerializedName("image_url")
    val imageUrl: String?,
    @SerializedName("language")
    val language: String,
    @SerializedName("published_at")
    val publishedAt: String,
    @SerializedName("source")
    val source: String,
    @SerializedName("categories")
    val categories: List<String>,
    @SerializedName("relevance_score")
    val relevanceScore: Double?
)