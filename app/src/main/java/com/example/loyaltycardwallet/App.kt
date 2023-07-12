package com.example.loyaltycardwallet

import android.app.Application
import com.example.loyaltycardwallet.di.DaggerAppComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job

class App: Application() {

    val appComponent = DaggerAppComponent
        .builder()
        .context(this)
        .scope(CoroutineScope(Job()))
        .build()
}