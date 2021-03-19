package com.game.database.rawg.common.utils

data class Event<out T>(val state: State, val data: T?, val error: Throwable?) {

    companion object {
        fun <T> isLoading(): Event<T> =
            Event(State.LOADING, null, null)

        fun <T> isSuccess(data: T?): Event<T> =
            Event(State.SUCCESS, data, null)

        fun <T> isError(error: Throwable?): Event<T> =
            Event(State.ERROR, null, error)
    }
}