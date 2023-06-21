package com.example.loyaltycardwallet

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.loyaltycardwallet.di.AppComponent
import com.example.loyaltycardwallet.di.DaggerAppComponent

class MainActivity : AppCompatActivity() {

    private lateinit var appComponent: AppComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        appComponent = (application as App).appComponent
        appComponent.getCardRepository()
    }
}