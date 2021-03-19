package com.game.database.rawg.data.remote.api

import com.game.database.rawg.data.model.detail.GameDetailResponse
import com.game.database.rawg.data.remote.response.GamesResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GameApiService {

    @GET("games")
    fun getListGames(
        @Query("page") page: Int,
        @Query("search") query: String?
    ): Single<GamesResponse>

    @GET("games/{id}")
    fun getDetailsGame(
        @Path("id") id: Int?
    ) : Single<GameDetailResponse>

    @GET("games/{id}/suggested")
    fun getSimilarGames(
        @Path("id") id: Int?
    ) : Single<GamesResponse>

}