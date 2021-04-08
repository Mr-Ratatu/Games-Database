package com.game.database.rawg.presentation.ui.fragment.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.rxjava3.cachedIn
import com.game.database.rawg.presentation.base.vm.BaseViewModel
import com.game.database.rawg.data.model.list.GameResult
import com.game.database.rawg.domain.usecases.GameListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.kotlin.subscribeBy
import javax.inject.Inject

@HiltViewModel
class GameListViewModel @Inject constructor(private val gameListUseCase: GameListUseCase) : BaseViewModel() {

    var queryField = ""

    private val queryResult = MutableLiveData<String>()

    private val _pagingGames = MutableLiveData<PagingData<GameResult>>()
    val pagingGames: LiveData<PagingData<GameResult>>
        get() = _pagingGames

    init {
        pagingData()
    }

    private fun pagingData() {
        gameListUseCase.getGameList(queryResult.value)
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
        queryField = query
        queryResult.value = query
        pagingData()
    }

    fun refreshList() {
        pagingData()
    }

}