package com.game.database.rawg.presentation.ui.adapters

import com.game.database.rawg.R
import com.game.database.rawg.presentation.base.adapter.BaseAdapter
import com.game.database.rawg.presentation.base.adapter.BaseAdapterListener
import com.game.database.rawg.presentation.base.adapter.DiffUtils
import com.game.database.rawg.data.model.list.GameResult
import com.game.database.rawg.data.model.list.ScreenshotsResult

class GeneralAdapter<T>(listener: BaseAdapterListener<T>? = null) : BaseAdapter<T>(DiffUtils(), listener) {

    override fun getItemViewType(position: Int) = when (getItem(position)) {
        is GameResult -> R.layout.item_similar_games
        is ScreenshotsResult -> R.layout.item_screenshots
        else -> R.layout.item_stores
    }

}