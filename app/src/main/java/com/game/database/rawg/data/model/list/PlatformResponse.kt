package com.game.database.rawg.data.model.list

import com.google.gson.annotations.SerializedName

data class PlatformResponse(
    @SerializedName("id")
    val id: Int,

    @SerializedName("slug")
    val slug: String,

    @SerializedName("name")
    val name: String
)
