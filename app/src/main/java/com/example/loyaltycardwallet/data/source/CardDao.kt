package com.example.loyaltycardwallet.data.source

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface CardDao {
    @Insert
    suspend fun insertInputCards(cards: List<InputCardEntity>)

    @Query("SELECT * FROM InputCards")
    suspend fun getInputCards(): List<InputCardEntity>
}