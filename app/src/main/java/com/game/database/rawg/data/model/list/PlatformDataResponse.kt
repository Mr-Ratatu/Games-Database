package com.game.database.rawg.data.model.list

import com.google.gson.annotations.SerializedName

data class PlatformDataResponse(
    @SerializedName("platform")
    val platform: PlatformResponse,
)