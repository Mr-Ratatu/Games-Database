package com.game.database.rawg.domain.repository

import androidx.lifecycle.LiveData
import com.game.database.rawg.data.model.list.GameResult

interface FavouriteGameRepository {

    fun getAllList(): LiveData<List<GameResult>>

}