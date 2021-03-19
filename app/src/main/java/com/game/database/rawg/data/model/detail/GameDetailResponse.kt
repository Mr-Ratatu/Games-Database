package com.game.database.rawg.data.model.detail


import com.google.gson.annotations.SerializedName

data class GameDetailResponse(
    @SerializedName("added_by_status")
    val addedByStatus: AddedByStatus,

    @SerializedName("background_image")
    val image: String,

    @SerializedName("background_image_additional")
    val backgroundImage: String,

    val description: String,

    val developers: List<Developer>,

    val genres: List<Genre>,

    val id: Int,

    val name: String,

    @SerializedName("name_original")
    val nameOriginal: String,

    val platforms: List<Platforms>,

    val publishers: List<Publisher>,

    val rating: Double,

    @SerializedName("ratings_count")
    val ratingsCount: Int,

    @SerializedName("reddit_description")
    val redditDescription: String,

    val released: String,

    @SerializedName("reviews_count")
    val reviewsCount: Int,

    val slug: String,

    val stores: List<StoreResponse>,

    val updated: String,

    val website: String,
)