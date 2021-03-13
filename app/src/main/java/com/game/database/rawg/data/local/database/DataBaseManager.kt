package com.game.database.rawg.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.game.database.rawg.data.local.converter.PlatformResponseConverter
import com.game.database.rawg.data.local.converter.ScreenshotsConverter
import com.game.database.rawg.data.local.converter.TagsResponseConverter
import com.game.database.rawg.data.local.dao.GamesDao
import com.game.database.rawg.data.model.list.GameResult

@Database(entities = [GameResult::class], version = 2, exportSchema = false)
@TypeConverters(
    PlatformResponseConverter::class,
    ScreenshotsConverter::class,
    TagsResponseConverter::class
)
abstract class DataBaseManager : RoomDatabase() {

    abstract fun gamesDao(): GamesDao

}