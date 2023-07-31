package com.example.loyaltycardwallet

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.loyaltycardwallet.di.AppComponent
import com.example.loyaltycardwallet.ui.AnimatedCardsActivity

class MainActivity : AppCompatActivity() {

    private lateinit var appComponent: AppComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        appComponent = (application as App).appComponent
        val intent = Intent(this, AnimatedCardsActivity::class.java)
        startActivity(intent)

    }
}