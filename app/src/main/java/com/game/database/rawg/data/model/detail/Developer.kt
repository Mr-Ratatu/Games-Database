package com.game.database.rawg.data.model.detail


import com.google.gson.annotations.SerializedName

data class Developer(
    @SerializedName("games_count")
    val gamesCount: Int,

    val id: Int,

    @SerializedName("image_background")
    val imageBackground: String,

    val name: String,

    val slug: String
)