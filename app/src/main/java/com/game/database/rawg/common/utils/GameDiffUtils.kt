package com.game.database.rawg.common.utils

import androidx.recyclerview.widget.DiffUtil
import com.game.database.rawg.data.model.list.GameResult

class GameDiffUtils : DiffUtil.ItemCallback<GameResult>() {
    override fun areItemsTheSame(oldItem: GameResult, newItem: GameResult): Boolean {
        return oldItem.gameId == newItem.gameId
    }

    override fun areContentsTheSame(oldItem: GameResult, newItem: GameResult): Boolean {
        return oldItem.gameId == newItem.gameId
    }
}