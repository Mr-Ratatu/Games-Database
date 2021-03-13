package com.game.database.rawg.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.game.database.rawg.data.model.list.GameResult

@Dao
interface GamesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(result: GameResult)

    @Delete
    fun delete(result: GameResult)

    @Query("SELECT * FROM gameresult")
    fun getAllList() : LiveData<List<GameResult>>

    @Query("SELECT * FROM gameresult WHERE gameId = :id")
    fun getGameById(id: Int?): GameResult?

}