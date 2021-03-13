package com.game.database.rawg

import android.app.Application
import com.game.database.rawg.di.dataBaseModule
import com.game.database.rawg.di.networkModule
import com.game.database.rawg.di.repositoryModule
import com.game.database.rawg.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            modules(
                listOf(
                    networkModule,
                    repositoryModule,
                    viewModelModule,
                    dataBaseModule
                )
            )
        }
    }

}