package com.game.database.rawg.data.local.converter

import androidx.room.TypeConverter
import com.game.database.rawg.data.model.list.PlatformDataResponse
import com.game.database.rawg.data.model.list.ScreenshotsResult
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class PlatformResponseConverter {

    @TypeConverter
    fun listToGson(list: List<PlatformDataResponse?>): String =
        Gson().toJson(list)

    @TypeConverter
    fun gsonToList(str: String?): List<PlatformDataResponse?> {
        if (str == null) return emptyList()

        val listType = object : TypeToken<List<Any>>() {}.type

        return Gson().fromJson(str, listType)
    }

}