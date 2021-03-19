package com.game.database.rawg.data.local.converter

import androidx.room.TypeConverter
import com.game.database.rawg.data.model.list.PlatformDataResponse
import com.game.database.rawg.data.model.list.ScreenshotsResult
import com.game.database.rawg.data.model.list.TagsResponse
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ListConverters {

    @TypeConverter
    fun listToGsonPlatform(list: List<PlatformDataResponse>): String =
        Gson().toJson(list)

    @TypeConverter
    fun gsonToListPlatform(str: String): List<PlatformDataResponse> {
        val listType = object : TypeToken<List<PlatformDataResponse>>() {}.type

        return Gson().fromJson(str, listType)
    }

    @TypeConverter
    fun listToGsonScreenshots(list: List<ScreenshotsResult?>): String = Gson().toJson(list)

    @TypeConverter
    fun gsonToListScreenshots(str: String?): List<ScreenshotsResult?> {
        if (str == null) return emptyList()

        val type = object : TypeToken<List<ScreenshotsResult>>() {}.type

        return Gson().fromJson(str, type)
    }

    @TypeConverter
    fun listToGsonTags(list: List<TagsResponse>): String = Gson().toJson(list)

    @TypeConverter
    fun gsonToListTags(str: String): List<TagsResponse> {
        val type = object : TypeToken<List<TagsResponse>>() {}.type

        return Gson().fromJson(str, type)
    }

}