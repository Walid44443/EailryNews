package com.walid44443.newsapp.domain.mappers

import com.walid44443.newsapp.data.local.entity.NewsEntity
import com.walid44443.newsapp.data.remote.dto.ArticleDto
import com.walid44443.newsapp.domain.model.News

// Convert NewsEntity to Domain News
fun NewsEntity.toDomain(): News {
    val categoryList = if (categories.isNotEmpty() && categories != "[]") {
        categories.removeSurrounding("[", "]")
            .split(",")
            .map { it.trim().removeSurrounding("\"") }
            .filter { it.isNotEmpty() }
    } else {
        emptyList()
    }

    return News(
        id = uuid,
        title = title,
        description = description,
        imageUrl = imageUrl,
        url = url,
        publishedAt = publishedAt,
        source = source,
        categories = categoryList,
        language = language,
        relevanceScore = relevanceScore
    )
}

// Convert ArticleDto to NewsEntity
fun ArticleDto.toEntity(): NewsEntity {
    return NewsEntity(
        uuid = java.util.UUID.randomUUID().toString(),
        title = title,
        description = description ?: "",
        imageUrl = imageUrl ?: "",
        url = url,
        publishedAt = publishedAt,
        source = source,
        categories = "[]",
        language = "en",
        relevanceScore = null
    )
}

// Convert list of ArticleDto to list of NewsEntity
fun List<ArticleDto>.toEntityList(): List<NewsEntity> {
    return map { it.toEntity() }
}