package com.game.database.rawg.ui.adapters.listeners

import android.view.View
import com.game.database.rawg.common.base.BaseAdapterListener
import com.game.database.rawg.data.model.list.GameResult

interface FavoriteListener: BaseAdapterListener<GameResult> {
    fun openDetailFragment(result: GameResult, poster: View)
}