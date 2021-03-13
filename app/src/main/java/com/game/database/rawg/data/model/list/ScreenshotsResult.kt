package com.game.database.rawg.data.model.list

import com.google.gson.annotations.SerializedName

data class ScreenshotsResult(
    @SerializedName("id")
    val id: Int,
    @SerializedName("image")
    val imageUrl: String
)