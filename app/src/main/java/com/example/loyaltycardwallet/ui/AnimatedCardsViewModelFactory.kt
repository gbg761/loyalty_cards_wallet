package com.example.loyaltycardwallet.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.loyaltycardwallet.data.CardRepository

class AnimatedCardsViewModelFactory(private val repository: CardRepository): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return AnimatedCardsViewModel(repository) as T
    }
}