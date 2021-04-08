package com.game.database.rawg.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.rxjava3.flowable
import com.game.database.rawg.data.model.list.GameResult
import com.game.database.rawg.data.remote.api.GameApiService
import com.game.database.rawg.data.remote.paging.GamePagingSource
import com.game.database.rawg.domain.repository.GameListRepository
import io.reactivex.rxjava3.core.Flowable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GameListRepositoryImpl @Inject constructor(
    private val api: GameApiService,
): GameListRepository {

    override fun getGameList(query: String?): Flowable<PagingData<GameResult>> =
        Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = false,
                prefetchDistance = 1
            ),
            pagingSourceFactory = { GamePagingSource(api, query) }
        ).flowable

}