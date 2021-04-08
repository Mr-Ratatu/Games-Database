package com.game.database.rawg.data.repository

import com.game.database.rawg.data.local.database.DataBaseManager
import com.game.database.rawg.domain.repository.FavouriteGameRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FavouriteGameRepositoryImpl @Inject constructor(private val manager: DataBaseManager) :
    FavouriteGameRepository {

    override fun getAllList() = manager.gamesDao().getAllList()

}