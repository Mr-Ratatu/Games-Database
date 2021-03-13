package com.game.database.rawg.data.local.converter

import androidx.room.TypeConverter
import com.game.database.rawg.data.model.list.TagsResponse
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class TagsResponseConverter {

    @TypeConverter
    fun listToGson(list: List<TagsResponse?>): String = Gson().toJson(list)

    @TypeConverter
    fun gsonToList(str: String?): List<TagsResponse?> {
        if (str == null) return emptyList()

        val type = object : TypeToken<List<Any>>() {}.type

        return Gson().fromJson(str, type)
    }

}