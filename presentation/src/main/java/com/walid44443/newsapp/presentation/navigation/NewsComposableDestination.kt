package com.walid44443.newsapp.presentation.navigation

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavDeepLink
import androidx.navigation.navDeepLink

interface NewsComposableDestination {
    val name: String
    val navComposableDestination: String
    val analyticsName: String
        get() = navComposableDestination

    fun getArguments(): List<NamedNavArgument> = emptyList()
}