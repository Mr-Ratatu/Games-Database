package com.game.database.rawg.domain.usecases

import androidx.paging.PagingData
import com.game.database.rawg.data.model.list.GameResult
import com.game.database.rawg.domain.repository.GameListRepository
import io.reactivex.rxjava3.core.Flowable
import javax.inject.Inject

class GameListUseCase @Inject constructor(private val repository: GameListRepository) {

    fun getGameList(query: String?): Flowable<PagingData<GameResult>> =
        repository.getGameList(query)

}