package com.example.loyaltycardwallet.data

import com.example.loyaltycardwallet.model.InputCard

interface CardRepository {

    fun readCardsInfo()

    suspend fun getCardsInfo(): List<InputCard>
}