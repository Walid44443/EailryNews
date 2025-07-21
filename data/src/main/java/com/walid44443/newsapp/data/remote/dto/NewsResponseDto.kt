package com.walid44443.newsapp.data.remote.dto

import com.google.gson.annotations.SerializedName

data class NewsResponseDto(
    @SerializedName("data")
    val articles: List<ArticleDto>,
    @SerializedName("meta")
    val meta: MetaDto
)
