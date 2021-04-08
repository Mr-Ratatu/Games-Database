package com.game.database.rawg.presentation.ui.fragment.favorite

import com.game.database.rawg.presentation.base.vm.BaseViewModel
import com.game.database.rawg.domain.usecases.FavoriteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(private val favoriteUseCase: FavoriteUseCase) :
    BaseViewModel() {

    fun getAllList() = favoriteUseCase.getAllList()

}