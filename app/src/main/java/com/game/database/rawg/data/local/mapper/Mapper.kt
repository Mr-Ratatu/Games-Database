package com.game.database.rawg.data.local.mapper

import com.game.database.rawg.data.local.entity.Games
import com.game.database.rawg.data.model.detail.GameDetailResponse

fun GameDetailResponse.toGameDetails() = Games(
    id = id,
    name = name,
    poster = image
)