package com.game.database.rawg.di

import com.game.database.rawg.data.repository.FavouriteGameRepository
import com.game.database.rawg.data.repository.GameDetailRepository
import com.game.database.rawg.data.repository.GameListRepository
import org.koin.dsl.module

val repositoryModule = module {

    single { GameListRepository(get()) }

    single { GameDetailRepository(get(), get()) }

    single { FavouriteGameRepository(get()) }

}