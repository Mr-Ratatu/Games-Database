package com.game.database.rawg.presentation.ui.adapters.favourite

import com.game.database.rawg.R
import com.game.database.rawg.presentation.base.adapter.BaseAdapter
import com.game.database.rawg.presentation.base.adapter.DiffUtils
import com.game.database.rawg.data.model.list.GameResult
import com.game.database.rawg.presentation.ui.adapters.listeners.FavoriteListener

class FavoriteAdapter(listener: FavoriteListener) : BaseAdapter<GameResult>(DiffUtils(), listener){

    override fun getItemViewType(position: Int) = R.layout.item_favorite

}