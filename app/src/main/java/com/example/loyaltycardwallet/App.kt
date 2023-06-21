package com.example.loyaltycardwallet

import android.app.Application
import com.example.loyaltycardwallet.di.DaggerAppComponent

class App: Application() {

    val appComponent = DaggerAppComponent
        .builder()
        .context(this)
        .build()
}