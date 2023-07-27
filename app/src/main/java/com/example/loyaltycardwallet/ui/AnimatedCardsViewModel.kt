package com.example.loyaltycardwallet.ui

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.loyaltycardwallet.data.CardRepository
import com.example.loyaltycardwallet.model.InputCard
import kotlinx.coroutines.launch
import kotlin.random.Random

class AnimatedCardsViewModel(private val repository: CardRepository) : ViewModel() {

    private val _cards = MutableLiveData<List<InputCard>>()
    val inputCards = _cards

    fun getInputCards() {
        viewModelScope.launch {
            val cards = repository.getCardsInfo()
            val randomSelectedCards = mutableListOf<InputCard>()
            for (i in 1..9) {
                val position = Random.nextInt(0, cards.size - 1)
                val card = cards[position]
                randomSelectedCards.add(card)
                Log.d("AnimatedCardsViewModel", "position = $position, shop name = ${card.shopName}")
            }
            inputCards.value = randomSelectedCards
        }
    }
}