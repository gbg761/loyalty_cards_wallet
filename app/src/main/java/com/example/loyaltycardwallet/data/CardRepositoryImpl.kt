package com.example.loyaltycardwallet.data

import com.example.loyaltycardwallet.data.source.CardDao

class CardRepositoryImpl(private val cardReader: CardReader, private val localDataSource: CardDao): CardRepository {
}