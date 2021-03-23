package com.game.database.rawg.extension

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

fun <T> Single<T>.applySchedulers(): Single<T> =
    observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(Schedulers.io())