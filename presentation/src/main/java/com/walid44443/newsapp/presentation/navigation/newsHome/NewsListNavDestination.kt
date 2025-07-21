package com.walid44443.newsapp.presentation.navigation.newsHome

import androidx.navigation.NamedNavArgument
import com.walid44443.newsapp.presentation.navigation.NewsComposableDestination
import com.walid44443.newsapp.presentation.navigation.NewsRoute

object NewsListDestination : NewsComposableDestination {
    override val name = "newsList"
    override val navComposableDestination = name

    override val analyticsName: String
        get() = "news_list_screen"

    override fun getArguments(): List<NamedNavArgument> = emptyList()

    data class NewsListArgumentsData(
        val category: String? = null
    )

    fun getNavigationRoute() = object : NewsRoute {
        override val route = name
    }
}