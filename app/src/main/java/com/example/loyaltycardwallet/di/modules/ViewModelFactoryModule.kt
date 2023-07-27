package com.example.loyaltycardwallet.di.modules

import com.example.loyaltycardwallet.data.CardRepository
import com.example.loyaltycardwallet.ui.AnimatedCardsViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class ViewModelFactoryModule {

    @Provides
    fun provideAnimatedCardsViewModelFactory(repository: CardRepository): AnimatedCardsViewModelFactory {
        return AnimatedCardsViewModelFactory(repository)
    }
}