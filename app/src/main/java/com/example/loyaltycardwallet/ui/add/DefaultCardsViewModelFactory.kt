package com.example.loyaltycardwallet.ui.add

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.loyaltycardwallet.data.CardRepository

class DefaultCardsViewModelFactory(private val repository: CardRepository): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return DefaultCardsViewModel(repository) as T
    }
}