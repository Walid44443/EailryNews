package com.walid44443.newsapp.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "news")
data class NewsEntity(
    @PrimaryKey
    val uuid: String,
    val title: String,
    val description: String?,
    val imageUrl: String?,
    val url: String,
    val publishedAt: String,
    val source: String,
    val categories: String, // JSON string of categories
    val language: String,
    val relevanceScore: Double?,
    val createdAt: Long = System.currentTimeMillis()
)