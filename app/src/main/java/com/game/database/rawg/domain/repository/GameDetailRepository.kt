package com.game.database.rawg.domain.repository

import com.game.database.rawg.data.model.detail.GameDetailResponse
import com.game.database.rawg.data.model.list.GameResult
import com.game.database.rawg.data.remote.response.GamesResponse
import io.reactivex.rxjava3.core.Single

interface GameDetailRepository {

    fun getDetailsGame(id: Int?): Single<GameDetailResponse>

    fun getSimilarGames(id: Int?): Single<GamesResponse>

    fun insert(result: GameResult)

    fun delete(result: GameResult)

    fun getGameById(result: GameResult): GameResult?

}