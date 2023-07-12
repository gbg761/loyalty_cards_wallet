package com.example.loyaltycardwallet.di

import android.content.Context
import com.example.loyaltycardwallet.data.CardReader
import com.example.loyaltycardwallet.data.CardRepository
import com.example.loyaltycardwallet.di.modules.ReaderModule
import com.example.loyaltycardwallet.di.modules.RepositoryModule
import com.example.loyaltycardwallet.di.modules.StorageModule
import dagger.BindsInstance
import dagger.Component
import kotlinx.coroutines.CoroutineScope

@Component(modules = [ReaderModule::class, StorageModule::class, RepositoryModule::class])
interface AppComponent {
    fun getCardReader(): CardReader

    fun getCardRepository(): CardRepository

    @Component.Builder
    interface AppComponentBuilder {
        fun build(): AppComponent

        @BindsInstance
        fun context(context: Context): AppComponentBuilder

        @BindsInstance
        fun scope(scope: CoroutineScope): AppComponentBuilder
    }
}