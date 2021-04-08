package com.game.database.rawg.presentation.ui.fragment.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.game.database.rawg.presentation.base.vm.BaseViewModel
import com.game.database.rawg.data.model.detail.GameDetailResponse
import com.game.database.rawg.presentation.extension.applySchedulers
import com.game.database.rawg.common.utils.Event
import com.game.database.rawg.data.model.detail.StoreResponse
import com.game.database.rawg.data.model.list.GameResult
import com.game.database.rawg.domain.usecases.GameDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.kotlin.subscribeBy
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val detailUseCase: GameDetailUseCase) : BaseViewModel() {

    private val _isFavorite = MutableLiveData<Boolean>()
    val isFavorite: LiveData<Boolean> get() = _isFavorite

    private val _stores = MutableLiveData<List<StoreResponse>>()
    val stores: LiveData<List<StoreResponse>> get() = _stores

    private val _shareData = MutableLiveData<String>()
    val shareData: LiveData<String> get() = _shareData

    private val _loadDetail = MutableLiveData<Event<GameDetailResponse>>()
    val loadDetail: LiveData<Event<GameDetailResponse>> get() = _loadDetail

    private val _similarGames = MutableLiveData<List<GameResult>>()
    val similarGames: LiveData<List<GameResult>> get() = _similarGames

    fun getSimilarGames(id: Int?) {
        detailUseCase.getSimilarGames(id)
            .applySchedulers()
            .subscribeBy(
                onSuccess = {
                    _similarGames.postValue(it.results)
                },
                onError = {
                    it.printStackTrace()
                }
            )
            .disposeOnCleared()
    }

    fun getDetailsGame(id: Int?) {
        detailUseCase.getDetailsGame(id)
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
            detailUseCase.insert(result)
            _isFavorite.postValue(true)
        } else {
            detailUseCase.delete(result)
            _isFavorite.postValue(false)
        }
    }

    fun showStores(stores: List<StoreResponse>) {
        _stores.postValue(stores)
    }

    fun shareData(data: String) {
        _shareData.postValue(data)
    }

    private fun getGameById(result: GameResult) = detailUseCase.getGameById(result)

    fun isFavorite(result: GameResult): Boolean {
        return getGameById(result) != null
    }

}
