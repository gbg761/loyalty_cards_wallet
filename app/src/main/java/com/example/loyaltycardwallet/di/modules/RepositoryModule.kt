package com.example.loyaltycardwallet.di.modules

import com.example.loyaltycardwallet.data.CardReader
import com.example.loyaltycardwallet.data.CardRepository
import com.example.loyaltycardwallet.data.CardRepositoryImpl
import com.example.loyaltycardwallet.data.source.CardDao
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineScope

@Module
class RepositoryModule {

    @Provides
    fun provideCardRepository(cardReader: CardReader, localDataSource: CardDao, scope: CoroutineScope): CardRepository {
        return CardRepositoryImpl(cardReader, localDataSource, scope)
    }
}