package com.game.database.rawg.data.model.list

import android.os.Parcelable
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.game.database.rawg.data.local.converter.ListConverters
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

@Parcelize
@Entity
data class GameResult(
    @SerializedName("id")
    @PrimaryKey
    val gameId: Int,

    @SerializedName("name")
    val name: String,

    @SerializedName("released")
    val released: String?,

    @SerializedName("background_image")
    val imageUrl: String?,

    @SerializedName("rating")
    val rating: Float,

    @SerializedName("rating_top")
    val ratingTop: Float,

    @SerializedName("ratings_count")
    val ratingsCount: Int,

    @SerializedName("short_screenshots")
    val screenshots: @RawValue List<ScreenshotsResult>,

    @SerializedName("platforms")
    val platforms: @RawValue List<PlatformDataResponse>,

    @SerializedName("updated")
    val updateGame: String,

    @SerializedName("tags")
    val gameTags: @RawValue List<TagsResponse>
) : Parcelable