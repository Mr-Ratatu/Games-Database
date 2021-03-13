package com.game.database.rawg.data.repository

import com.game.database.rawg.data.local.database.DataBaseManager

class FavouriteGameRepository(manager: DataBaseManager) {

    val getAllList = manager.gamesDao().getAllList()

}