package com.game.database.rawg.data.repository

import com.game.database.rawg.data.local.database.DataBaseManager
import com.game.database.rawg.data.model.detail.GameDetailResponse
import com.game.database.rawg.data.model.list.GameResult
import com.game.database.rawg.data.remote.api.GameApiService
import com.game.database.rawg.extension.applySchedulers
import io.reactivex.rxjava3.core.Single

class GameDetailRepository(
    private val api: GameApiService,
    private val manager: DataBaseManager
) {

    val getAllList = manager.gamesDao().getAllList()

    fun getDetailsGame(id: Int?): Single<GameDetailResponse> = api.getDetailsGame(id)

    fun insert(result: GameResult) {
        Single.fromCallable { manager.gamesDao().insert(result) }
            .applySchedulers()
            .subscribe()
    }

    fun delete(result: GameResult) {
        Single.fromCallable { manager.gamesDao().delete(result) }
            .applySchedulers()
            .subscribe()
    }

    fun getGameById(result: GameResult) = manager.gamesDao().getGameById(result.gameId)

}