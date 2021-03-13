package com.game.database.rawg.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Games(
    @PrimaryKey
    val id: Int,

    val name: String,

    val poster: String?
)
