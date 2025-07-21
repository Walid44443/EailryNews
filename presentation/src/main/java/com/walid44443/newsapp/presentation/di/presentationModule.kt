package com.walid44443.newsapp.presentation.di

import com.walid44443.newsapp.presentation.navigation.newsDetails.NewsDetailsDestination
import com.walid44443.newsapp.presentation.newsDetails.NewsDetailsViewModel
import com.walid44443.newsapp.presentation.newsHome.NewsViewModel
import com.walid44443.newsapp.presentation.utils.networkConnectivity.NetworkConnectivityObserver
import com.walid44443.newsapp.presentation.utils.networkConnectivity.NetworkConnectivityObserverImpl
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {

    factory<NetworkConnectivityObserver> {
        NetworkConnectivityObserverImpl(
            context = androidContext()
        )
    }

    viewModel {
        NewsViewModel(
            networkConnectivityObserver = get(),
            newsUseCases = get()
        )
    }

    viewModel { (arguments: NewsDetailsDestination.NewsDetailsArgumentsData) ->
        NewsDetailsViewModel(
            arguments = arguments,
            getNewsByIdUseCase = get()
        )
    }
}