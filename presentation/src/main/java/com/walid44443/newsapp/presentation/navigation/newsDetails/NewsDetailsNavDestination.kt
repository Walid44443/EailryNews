package com.walid44443.newsapp.presentation.navigation.newsDetails

import android.os.Bundle
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.walid44443.newsapp.presentation.navigation.EMPTY_PARAM
import com.walid44443.newsapp.presentation.navigation.NewsComposableDestination
import com.walid44443.newsapp.presentation.navigation.NewsRoute
import com.walid44443.newsapp.presentation.navigation.decodeUrl
import com.walid44443.newsapp.presentation.navigation.encodeUrl

object NewsDetailsDestination : NewsComposableDestination {
    override val name = "newsDetails"
    override val navComposableDestination = name
        .plus("/{${NewsDetailsArguments.NEWS_ID}}")
        .plus("/{${NewsDetailsArguments.NEWS_TITLE}}")
        .plus("/{${NewsDetailsArguments.NEWS_IMAGE_URL}}")
        .plus("/{${NewsDetailsArguments.NEWS_CONTENT}}")

    override val analyticsName: String
        get() = "news_details_screen"

    override fun getArguments(): List<NamedNavArgument> = listOf(
        navArgument(name = NewsDetailsArguments.NEWS_ID) {
            type = NavType.StringType
        },
        navArgument(name = NewsDetailsArguments.NEWS_TITLE) {
            type = NavType.StringType
        },
        navArgument(name = NewsDetailsArguments.NEWS_IMAGE_URL) {
            type = NavType.StringType
        },
        navArgument(name = NewsDetailsArguments.NEWS_CONTENT) {
            type = NavType.StringType
        }
    )

    data class NewsDetailsArgumentsData(
        val newsId: String?,
        val newsTitle: String?,
        val newsImageUrl: String?,
        val newsContent: String?
    )

    fun parseArguments(arguments: Bundle?): NewsDetailsArgumentsData =
        NewsDetailsArgumentsData(
            newsId = with(runCatching {
                arguments?.getString(NewsDetailsArguments.NEWS_ID)
            }.getOrDefault(null)) {
                when (this) {
                    EMPTY_PARAM -> null
                    else -> this
                }
            },

            newsTitle = with(runCatching {
                arguments?.getString(NewsDetailsArguments.NEWS_TITLE)?.decodeUrl()
            }.getOrDefault(null)) {
                when (this) {
                    EMPTY_PARAM -> null
                    else -> this
                }
            },

            newsImageUrl = with(runCatching {
                arguments?.getString(NewsDetailsArguments.NEWS_IMAGE_URL)?.decodeUrl()
            }.getOrDefault(null)) {
                when (this) {
                    EMPTY_PARAM -> null
                    else -> this
                }
            },

            newsContent = with(runCatching {
                arguments?.getString(NewsDetailsArguments.NEWS_CONTENT)?.decodeUrl()
            }.getOrDefault(null)) {
                when (this) {
                    EMPTY_PARAM -> null
                    else -> this
                }
            }
        )

    fun getNavigationRoute(
        newsId: String,
        newsTitle: String = EMPTY_PARAM,
        newsImageUrl: String = EMPTY_PARAM,
        newsContent: String = EMPTY_PARAM
    ) = object : NewsRoute {
        override val route = name
            .plus("/")
            .plus(newsId)
            .plus("/")
            .plus(newsTitle.encodeUrl())
            .plus("/")
            .plus(newsImageUrl.encodeUrl())
            .plus("/")
            .plus(newsContent.encodeUrl())
    }
}