package com.example.loyaltycardwallet.di.modules

import android.content.Context
import com.example.loyaltycardwallet.data.CardReader
import dagger.Module
import dagger.Provides

@Module
class ReaderModule() {

    @Provides
    fun provideCardReader(context: Context): CardReader = CardReader(context)
}