package com.game.database.rawg.data.model.detail


import com.google.gson.annotations.SerializedName

data class Clip(
    val clip: String,
    val clips: Clips,
    val preview: String,
    val video: String
)