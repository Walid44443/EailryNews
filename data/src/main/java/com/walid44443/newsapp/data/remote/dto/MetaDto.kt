package com.walid44443.newsapp.data.remote.dto

import com.google.gson.annotations.SerializedName

data class MetaDto(
    @SerializedName("found")
    val found: Int,
    @SerializedName("returned")
    val returned: Int,
    @SerializedName("limit")
    val limit: Int,
    @SerializedName("page")
    val page: Int
)