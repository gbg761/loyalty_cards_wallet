package com.example.loyaltycardwallet.data.source

import androidx.room.Dao
import androidx.room.Insert
import com.example.loyaltycardwallet.model.InputCard

@Dao
interface CardDao {
    @Insert
    fun insertInputCards(vararg cards: InputCard)
}