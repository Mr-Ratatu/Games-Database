package com.game.database.rawg.di

import android.content.Context
import androidx.room.Room
import com.game.database.rawg.common.utils.Constants.Companion.DB_NAME
import com.game.database.rawg.data.local.database.DataBaseManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataBaseModules {

    @Provides
    @Singleton
    fun provideRoom(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, DataBaseManager::class.java, DB_NAME)
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()

    @Provides
    @Singleton
    fun providesGamesDao(db: DataBaseManager) = db.gamesDao()

}