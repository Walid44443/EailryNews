package com.walid44443.newsapp.presentation.navigation

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.walid44443.newsapp.presentation.navigation.newsDetails.NewsDetailsDestination
import com.walid44443.newsapp.presentation.navigation.newsHome.NewsListDestination
import com.walid44443.newsapp.presentation.newsDetails.NewsDetailsScreen
import com.walid44443.newsapp.presentation.newsHome.NewsHomeScreen
import com.walid44443.newsapp.presentation.newsHome.NewsViewModel
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun NewsAppNavigation(
    navController: NavHostController = rememberNavController()
) {
    SharedTransitionLayout {
        NavHost(
            navController = navController,
            startDestination = NewsListDestination.navComposableDestination
        ) {
            composable(
                route = NewsListDestination.navComposableDestination,
                arguments = NewsListDestination.getArguments()
            ) {
                val homeNewsViewModel = koinViewModel<NewsViewModel>()
                NewsHomeScreen(
                    viewModel = homeNewsViewModel,
                    animatedVisibilityScope = this@composable,
                    onNavigateToDetail = { news ->
                        navController.navigate(
                            NewsDetailsDestination.getNavigationRoute(
                                newsId = news.id,
                                newsTitle = news.title,
                                newsImageUrl = news.imageUrl.orEmpty(),
                                newsContent = news.description.orEmpty()
                            ).route
                        )
                    }
                )
            }

            composable(
                route = NewsDetailsDestination.navComposableDestination,
                arguments = NewsDetailsDestination.getArguments()
            ) { backStackEntry ->
                val arguments = NewsDetailsDestination.parseArguments(backStackEntry.arguments)
                NewsDetailsScreen(
                    arguments = arguments,
                    animatedVisibilityScope = this@composable,
                    onBackClick = {
                        navController.popBackStack()
                    }
                )
            }
        }
    }
}