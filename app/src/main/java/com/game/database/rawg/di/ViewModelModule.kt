package com.game.database.rawg.di

import com.game.database.rawg.ui.fragment.detail.DetailViewModel
import com.game.database.rawg.ui.fragment.favorite.FavoriteViewModel
import com.game.database.rawg.ui.fragment.list.GameListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    viewModel { GameListViewModel(get()) }

    viewModel { DetailViewModel(get()) }

    viewModel { FavoriteViewModel(get()) }

}