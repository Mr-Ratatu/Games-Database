package com.game.database.rawg.ui.fragment.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.rxjava3.cachedIn
import com.game.database.rawg.common.base.BaseViewModel
import com.game.database.rawg.common.utils.Event
import com.game.database.rawg.data.model.list.GameResult
import com.game.database.rawg.data.repository.GameListRepository
import io.reactivex.rxjava3.kotlin.subscribeBy

class GameListViewModel(private val repository: GameListRepository) : BaseViewModel() {

    private val queryResult = MutableLiveData<String>()

    private val _pagingGames = MutableLiveData<PagingData<GameResult>>()
    val pagingGames: LiveData<PagingData<GameResult>>
        get() = _pagingGames

    init {
        pagingData()
    }

    private fun pagingData() {
        repository.getGameList(queryResult.value)
            .cachedIn(viewModelScope)
            .subscribeBy(
                onNext = {
                    _pagingGames.postValue(it)
                },
                onError = {
                    it.printStackTrace()
                }
            )
            .disposeOnCleared()
    }

    fun search(query: String) {
        queryResult.value = query
        pagingData()
    }

    fun refreshList() {
        pagingData()
    }

}