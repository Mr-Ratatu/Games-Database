package com.game.database.rawg.data.model.detail


import com.google.gson.annotations.SerializedName

data class Clips(
    val full: String,
    @SerializedName("320")
    val x320: String,
    @SerializedName("640")
    val x640: String
)