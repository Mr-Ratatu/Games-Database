package com.game.database.rawg.data.remote.paging

import androidx.paging.PagingState
import androidx.paging.rxjava3.RxPagingSource
import com.game.database.rawg.data.model.list.GameResult
import com.game.database.rawg.data.remote.response.GamesResponse
import com.game.database.rawg.common.utils.Constants.Companion.STARTING_INDEX
import com.game.database.rawg.data.remote.api.GameApiService
import io.reactivex.rxjava3.core.Single

class GamePagingSource(
    private val api: GameApiService,
    private val query: String?
) : RxPagingSource<Int, GameResult>() {

    override fun getRefreshKey(state: PagingState<Int, GameResult>): Int? {
        return state.anchorPosition
    }

    override fun loadSingle(params: LoadParams<Int>): Single<LoadResult<Int, GameResult>> {
        val position = params.key ?: STARTING_INDEX

        return api.getListGames(position, query)
            .map { response -> toLoadResult(response, position) }
            .onErrorReturn { t -> LoadResult.Error(t) }
    }

    private fun toLoadResult(response: GamesResponse, position: Int): LoadResult<Int, GameResult> {
        return LoadResult.Page(
            data = response.results,
            prevKey = if (position == 1) null else position.minus(1),
            nextKey = if (position == response.count) null else position.plus(1)
        )
    }

}