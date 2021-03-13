package com.game.database.rawg.ui.fragment.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.game.database.rawg.common.base.BaseViewModel
import com.game.database.rawg.data.model.detail.GameDetailResponse
import com.game.database.rawg.data.repository.GameDetailRepository
import com.game.database.rawg.extension.applySchedulers
import com.game.database.rawg.common.utils.Event
import com.game.database.rawg.data.model.list.GameResult
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.kotlin.subscribeBy

class DetailViewModel(private val repository: GameDetailRepository) : BaseViewModel() {

    private val _loadDetail = MutableLiveData<Event<GameDetailResponse>>()
    val loadDetail: LiveData<Event<GameDetailResponse>>
        get() = _loadDetail

    fun getDetailsGame(id: Int?) {
        repository.getDetailsGame(id)
            .doOnSubscribe { _loadDetail.postValue(Event.isLoading()) }
            .applySchedulers()
            .subscribeBy(
                onSuccess = { data ->
                    _loadDetail.postValue(Event.isSuccess(data))
                },
                onError = { error ->
                    error.printStackTrace()
                    _loadDetail.postValue(Event.isError(error))
                }
            )
            .disposeOnCleared()
    }

    fun addToFavourite(result: GameResult) {
        if (isFavorite(result)) repository.delete(result) else
            repository.insert(result)
    }

    fun insert(gameResult: GameResult) =
        repository.insert(gameResult)

    fun delete(gameResult: GameResult) =
        repository.delete(gameResult)

    private fun getGameById(result: GameResult) =
        Single.fromCallable { repository.getGameById(result) }
            .applySchedulers()
            .subscribe()

    fun isFavorite(result: GameResult): Boolean {
        return getGameById(result) != null
    }

}
