package com.game.database.rawg.di

import androidx.room.Room
import com.game.database.rawg.data.local.database.DataBaseManager
import com.game.database.rawg.common.utils.Constants.Companion.DB_NAME
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val dataBaseModule = module {

    single {
        Room.databaseBuilder(
            androidContext(),
            DataBaseManager::class.java,
            DB_NAME
        ).fallbackToDestructiveMigration()
            .build()
    }

    single { get<DataBaseManager>().gamesDao() }

}