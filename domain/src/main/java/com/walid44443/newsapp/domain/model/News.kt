package com.walid44443.newsapp.domain.model

data class News(
    val id: String,
    val title: String,
    val description: String?,
    val imageUrl: String?,
    val url: String,
    val publishedAt: String,
    val source: String,
    val categories: List<String>,
    val language: String,
    val relevanceScore: Double?
)