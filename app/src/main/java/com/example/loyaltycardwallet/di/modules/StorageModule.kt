package com.example.loyaltycardwallet.di.modules

import android.content.Context
import androidx.room.Room
import com.example.loyaltycardwallet.data.source.CardDao
import com.example.loyaltycardwallet.data.source.CardDatabase
import dagger.Module
import dagger.Provides

@Module
class StorageModule() {

    @Provides
    fun provideAppDatabase(context: Context): CardDatabase {
        return Room.databaseBuilder(
            context,
            CardDatabase::class.java,
            "CardDatabase.db"
        ).build()
    }

    @Provides
    fun provideCardDao(database: CardDatabase): CardDao = database.cardDao()

}