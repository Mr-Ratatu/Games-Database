package com.game.database.rawg.ui.adapters.favourite

import com.game.database.rawg.R
import com.game.database.rawg.common.base.BaseAdapter
import com.game.database.rawg.common.base.DiffUtils
import com.game.database.rawg.data.model.list.GameResult
import com.game.database.rawg.ui.adapters.listeners.FavoriteListener

class FavoriteAdapter(listener: FavoriteListener) : BaseAdapter<GameResult>(DiffUtils(), listener){

    override fun getItemViewType(position: Int) = R.layout.item_favorite

}