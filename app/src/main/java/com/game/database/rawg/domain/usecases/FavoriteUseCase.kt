package com.game.database.rawg.domain.usecases

import com.game.database.rawg.domain.repository.FavouriteGameRepository
import javax.inject.Inject

class FavoriteUseCase @Inject constructor(private val repository: FavouriteGameRepository) {

    fun getAllList() = repository.getAllList()

}