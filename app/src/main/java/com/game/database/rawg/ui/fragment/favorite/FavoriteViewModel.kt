package com.game.database.rawg.ui.fragment.favorite

import com.game.database.rawg.common.base.BaseViewModel
import com.game.database.rawg.data.repository.FavouriteGameRepository

class FavoriteViewModel(repository: FavouriteGameRepository) : BaseViewModel() {

    val getAllList = repository.getAllList

}