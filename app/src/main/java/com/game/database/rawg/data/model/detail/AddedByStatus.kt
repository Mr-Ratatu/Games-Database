package com.game.database.rawg.data.model.detail


import com.google.gson.annotations.SerializedName

data class AddedByStatus(
    val beaten: Int,
    val dropped: Int,
    val owned: Int,
    val playing: Int,
    val toplay: Int,
    val yet: Int
)