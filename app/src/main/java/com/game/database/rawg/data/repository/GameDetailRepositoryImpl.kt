package com.game.database.rawg.data.repository

import com.game.database.rawg.data.local.database.DataBaseManager
import com.game.database.rawg.data.model.detail.GameDetailResponse
import com.game.database.rawg.data.model.list.GameResult
import com.game.database.rawg.data.remote.api.GameApiService
import com.game.database.rawg.data.remote.response.GamesResponse
import com.game.database.rawg.domain.repository.GameDetailRepository
import com.game.database.rawg.presentation.extension.applySchedulers
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GameDetailRepositoryImpl @Inject constructor(
    private val api: GameApiService,
    private val manager: DataBaseManager,
) : GameDetailRepository {

    override fun getDetailsGame(id: Int?): Single<GameDetailResponse> = api.getDetailsGame(id)

    override fun getSimilarGames(id: Int?): Single<GamesResponse> = api.getSimilarGames(id)

    override fun insert(result: GameResult) {
        Single.fromCallable { manager.gamesDao().insert(result) }
            .applySchedulers()
            .subscribe()
    }

    override fun delete(result: GameResult) {
        Single.fromCallable { manager.gamesDao().delete(result) }
            .applySchedulers()
            .subscribe()
    }

    override fun getGameById(result: GameResult) = manager.gamesDao().getGameById(result.gameId)

}