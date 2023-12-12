package com.example.loyaltycardwallet.ui.add

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.loyaltycardwallet.data.CardRepository
import com.example.loyaltycardwallet.model.CardType
import com.example.loyaltycardwallet.model.InputCard
import com.example.loyaltycardwallet.model.InputCardWithType
import kotlinx.coroutines.launch

class DefaultCardsViewModel(private val repository: CardRepository) : ViewModel() {

    private val _cards = MutableLiveData<List<InputCardWithType>>()
    val inputCards = _cards

    init {
        getInputCards()
    }

    private fun getInputCards() {
        viewModelScope.launch {
            val cards = repository.getCardsInfo()
            val cardsWithType = cards.mapIndexed { i: Int, inputCard: InputCard ->
                if (i == 0) {
                    InputCardWithType(inputCard.shopName[i], inputCard.shopName, inputCard.cardName, inputCard.color, CardType.CARD_WITH_GROUP_NAME)
                } else {
                    val prevCard = cards[i - 1]
                    if (inputCard.shopName[0].isLetter()) {
                        if (prevCard.shopName[0] != inputCard.shopName[0]) {
                            InputCardWithType(inputCard.shopName[0], inputCard.shopName, inputCard.cardName, inputCard.color, CardType.CARD_WITH_GROUP_NAME)
                        } else {
                            InputCardWithType(null, inputCard.shopName, inputCard.cardName, inputCard.color, CardType.CARD_WO_GROUP_NAME)
                        }
                    } else {
                        if (prevCard.shopName[0].isLetter()) {
                            InputCardWithType('#', inputCard.shopName, inputCard.cardName, inputCard.color, CardType.CARD_WITH_GROUP_NAME)
                        } else {
                            InputCardWithType(null, inputCard.shopName, inputCard.cardName, inputCard.color, CardType.CARD_WO_GROUP_NAME)
                        }
                    }
                }
            }
            _cards.value = cardsWithType
        }
    }
}