package com.game.database.rawg.ui.adapters.listeners

import com.game.database.rawg.common.base.BaseAdapterListener
import com.game.database.rawg.data.model.detail.StoreResponse

interface StoresListener: BaseAdapterListener<StoreResponse> {
    fun onClickLinkStores(store: StoreResponse)
}