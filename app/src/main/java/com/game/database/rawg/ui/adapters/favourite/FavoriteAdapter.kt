package com.game.database.rawg.ui.adapters.favourite

import android.view.View
import com.game.database.rawg.R
import com.game.database.rawg.common.base.BaseAdapter
import com.game.database.rawg.common.base.BaseAdapterListener
import com.game.database.rawg.common.base.DiffUtils
import com.game.database.rawg.data.model.list.GameResult

class FavoriteAdapter(listener: FavoriteListener) : BaseAdapter<GameResult>(DiffUtils(), listener){

    interface FavoriteListener: BaseAdapterListener<GameResult> {
        fun openDetailFragment(result: GameResult, poster: View)
    }

    override fun getItemViewType(position: Int) = R.layout.item_favorite

}