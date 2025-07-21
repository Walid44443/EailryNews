package com.walid44443.newsapp.presentation.navigation

import java.net.URLDecoder
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

const val EMPTY_PARAM = "empty"

fun String.encodeUrl(): String {
    return if (this.isBlank()) {
        EMPTY_PARAM
    } else {
        URLEncoder.encode(this, StandardCharsets.UTF_8.toString())
    }
}

fun String.decodeUrl(): String {
    return if (this == EMPTY_PARAM) {
        ""
    } else {
        URLDecoder.decode(this, StandardCharsets.UTF_8.toString())
    }
}