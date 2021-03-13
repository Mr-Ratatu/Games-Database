package com.game.database.rawg.data.model.list

import com.google.gson.annotations.SerializedName

data class TagsResponse(
    @SerializedName("id")
    val id: Int,

    @SerializedName("name")
    val tags: String
)