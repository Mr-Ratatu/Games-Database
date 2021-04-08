package com.game.database.rawg.domain.usecases

import com.game.database.rawg.data.model.detail.GameDetailResponse
import com.game.database.rawg.data.model.list.GameResult
import com.game.database.rawg.data.remote.response.GamesResponse
import com.game.database.rawg.domain.repository.GameDetailRepository
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class GameDetailUseCase @Inject constructor(val repository: GameDetailRepository) {

    fun getDetailsGame(id: Int?): Single<GameDetailResponse> = repository.getDetailsGame(id)

    fun getSimilarGames(id: Int?): Single<GamesResponse> = repository.getSimilarGames(id)

    fun insert(result: GameResult) = repository.insert(result)

    fun delete(result: GameResult) = repository.delete(result)

    fun getGameById(result: GameResult): GameResult? = repository.getGameById(result)

}