package com.game.database.rawg.data.remote.response

import com.game.database.rawg.data.model.list.GameResult
import com.google.gson.annotations.SerializedName

data class GamesResponse(
    @SerializedName("count")
    val count: Int,

    @SerializedName("results")
    val results: List<GameResult>
)










