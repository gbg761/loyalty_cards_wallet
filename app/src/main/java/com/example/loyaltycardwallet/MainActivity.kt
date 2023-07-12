package com.example.loyaltycardwallet

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.loyaltycardwallet.di.AppComponent

class MainActivity : AppCompatActivity() {

    private lateinit var appComponent: AppComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        appComponent = (application as App).appComponent
        val cardRepository = appComponent.getCardRepository()
        //cardRepository.readCardsInfo()
        cardRepository.getCardsInfo()
    }
}