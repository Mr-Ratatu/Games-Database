package com.game.database.rawg.presentation.ui.adapters.listeners

import com.game.database.rawg.presentation.base.adapter.BaseAdapterListener
import com.game.database.rawg.data.model.detail.StoreResponse

interface StoresListener: BaseAdapterListener<StoreResponse> {
    fun onClickLinkStores(store: StoreResponse)
}