package com.game.database.rawg.ui.adapters.stores

import com.game.database.rawg.R
import com.game.database.rawg.common.base.BaseAdapter
import com.game.database.rawg.common.base.DiffUtils
import com.game.database.rawg.data.model.detail.StoreResponse

class StoresAdapter : BaseAdapter<StoreResponse>(DiffUtils()) {

    override fun getItemViewType(position: Int) = R.layout.item_stores

}