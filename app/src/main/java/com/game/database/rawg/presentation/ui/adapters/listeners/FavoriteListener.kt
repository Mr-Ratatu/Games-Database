package com.game.database.rawg.presentation.ui.adapters.listeners

import android.view.View
import com.game.database.rawg.presentation.base.adapter.BaseAdapterListener
import com.game.database.rawg.data.model.list.GameResult

interface FavoriteListener: BaseAdapterListener<GameResult> {
    fun openDetailFragment(result: GameResult, poster: View)
}