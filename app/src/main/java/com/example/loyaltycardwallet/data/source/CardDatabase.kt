package com.example.loyaltycardwallet.data.source

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [InputCardEntity::class], version = 1, exportSchema = true)
abstract class CardDatabase : RoomDatabase() {

    abstract fun cardDao(): CardDao
}