package com.game.database.rawg.data.model.detail

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

@Parcelize
data class StoreResponse(
    val id: Int,

    val store: @RawValue Stores,

    val url: String
) : Parcelable