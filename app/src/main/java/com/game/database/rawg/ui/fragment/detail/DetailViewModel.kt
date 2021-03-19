package com.game.database.rawg.ui.fragment.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.game.database.rawg.common.base.BaseViewModel
import com.game.database.rawg.data.model.detail.GameDetailResponse
import com.game.database.rawg.data.repository.GameDetailRepository
import com.game.database.rawg.extension.applySchedulers
import com.game.database.rawg.common.utils.Event
import com.game.database.rawg.data.model.detail.StoreResponse
import com.game.database.rawg.data.model.list.GameResult
import com.game.database.rawg.data.remote.response.GamesResponse
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.kotlin.subscribeBy

class DetailViewModel(private val repository: GameDetailRepository) : BaseViewModel() {

    private val _isFavorite = MutableLiveData<Boolean>()
    val isFavorite: LiveData<Boolean>
        get() = _isFavorite

    private val _stores = MutableLiveData<List<StoreResponse>>()
    val stores: LiveData<List<StoreResponse>>
        get() = _stores

    private val _shareData = MutableLiveData<String>()
    val shareData: LiveData<String>
        get() = _shareData

    private val _loadDetail = MutableLiveData<Event<GameDetailResponse>>()
    val loadDetail: LiveData<Event<GameDetailResponse>>
        get() = _loadDetail

    private val _similarGames = MutableLiveData<GamesResponse>()
    val similarGames: LiveData<GamesResponse>
        get() = _similarGames

    fun getSimilarGames(id: Int?) {
        repository.getSimilarGames(id)
            .applySchedulers()
            .subscribeBy(
                onSuccess = {
                    _similarGames.postValue(it)
                },
                onError = {
                    it.printStackTrace()
                }
            )
            .disposeOnCleared()
    }

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

    fun addOrRemoveToFavourite(result: GameResult) {
        if (getGameById(result) == null) {
            repository.insert(result)
            _isFavorite.postValue(true)
        } else {
            repository.delete(result)
            _isFavorite.postValue(false)
        }
    }

    fun showStores(stores: List<StoreResponse>) {
        _stores.postValue(stores)
    }

    fun shareData(data: String) {
        _shareData.postValue(data)
    }

    private fun getGameById(result: GameResult) = repository.getGameById(result)

    fun isFavorite(result: GameResult): Boolean {
        return getGameById(result) != null
    }

}
