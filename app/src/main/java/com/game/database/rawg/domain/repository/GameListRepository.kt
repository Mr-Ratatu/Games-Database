package com.game.database.rawg.domain.repository

import androidx.paging.PagingData
import com.game.database.rawg.data.model.list.GameResult
import io.reactivex.rxjava3.core.Flowable

interface GameListRepository {

    fun getGameList(query: String?): Flowable<PagingData<GameResult>>

}