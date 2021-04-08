package com.game.database.rawg.di

import com.game.database.rawg.data.local.database.DataBaseManager
import com.game.database.rawg.data.remote.api.GameApiService
import com.game.database.rawg.data.repository.FavouriteGameRepositoryImpl
import com.game.database.rawg.data.repository.GameDetailRepositoryImpl
import com.game.database.rawg.data.repository.GameListRepositoryImpl
import com.game.database.rawg.domain.repository.FavouriteGameRepository
import com.game.database.rawg.domain.repository.GameDetailRepository
import com.game.database.rawg.domain.repository.GameListRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideFavoriteRepo(db: DataBaseManager): FavouriteGameRepository =
        FavouriteGameRepositoryImpl(db)

    @Provides
    @Singleton
    fun provideDetailRepo(api: GameApiService, db: DataBaseManager): GameDetailRepository =
        GameDetailRepositoryImpl(api, db)

    @Provides
    @Singleton
    fun provideListRepo(api: GameApiService): GameListRepository =
        GameListRepositoryImpl(api)

}