package com.game.database.rawg.ui.adapters.similar

import com.game.database.rawg.R
import com.game.database.rawg.common.base.BaseAdapter
import com.game.database.rawg.common.base.DiffUtils
import com.game.database.rawg.data.model.list.GameResult

class SimilarGamesAdapter: BaseAdapter<GameResult>(DiffUtils()) {

    override fun getItemViewType(position: Int) = R.layout.item_similar_games

}