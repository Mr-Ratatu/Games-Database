package com.game.database.rawg.ui.adapters.screenshots

import com.game.database.rawg.R
import com.game.database.rawg.common.base.BaseAdapter
import com.game.database.rawg.common.base.DiffUtils
import com.game.database.rawg.data.model.list.ScreenshotsResult

class ScreenshotsAdapter : BaseAdapter<ScreenshotsResult>(DiffUtils()) {

    override fun getItemViewType(position: Int) = R.layout.item_screenshots

}