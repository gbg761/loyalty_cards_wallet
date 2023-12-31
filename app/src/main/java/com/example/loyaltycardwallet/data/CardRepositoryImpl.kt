package com.example.loyaltycardwallet.data

import android.util.Log
import com.example.loyaltycardwallet.data.source.CardDao
import com.example.loyaltycardwallet.model.InputCard
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class CardRepositoryImpl(
    private val cardReader: CardReader,
    private val localDataSource: CardDao,
    private val scope: CoroutineScope
) : CardRepository {

    override fun readCardsInfo() {
        val cards: List<InputCard> = cardReader.read()
        scope.launch {
            localDataSource.insertInputCards(cards.toLocal())
            Log.d("Repository", "cards.size = ${cards.size}")
        }
    }

    override suspend fun getCardsInfo(): List<InputCard> {
        val cards = localDataSource.getInputCards()
        return cards.toExternal()
    }
}