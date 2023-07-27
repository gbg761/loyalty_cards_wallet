package com.example.loyaltycardwallet.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.get
import androidx.core.view.size
import androidx.lifecycle.ViewModelProvider
import com.example.loyaltycardwallet.App
import com.example.loyaltycardwallet.R
import com.example.loyaltycardwallet.databinding.ActivityAnimatedCardsBinding
import com.example.loyaltycardwallet.di.AppComponent
import com.example.loyaltycardwallet.model.InputCard

class AnimatedCardsActivity : AppCompatActivity() {

    private lateinit var appComponent: AppComponent
    private lateinit var binding: ActivityAnimatedCardsBinding

    private lateinit var animatedCardsViewModel: AnimatedCardsViewModel

    //
    private lateinit var firstRow: LinearLayout
    private lateinit var secondRow: LinearLayout
    private lateinit var thirdRow: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAnimatedCardsBinding.inflate(layoutInflater)
        val rootView = binding.root
        setContentView(rootView)

        appComponent = (application as App).appComponent
        val factory = appComponent.getAnimatedCardsViewModelFactory()
        animatedCardsViewModel = ViewModelProvider(this, factory)[AnimatedCardsViewModel::class.java]

        animatedCardsViewModel.inputCards.observe(this) { randomCards: List<InputCard> ->
            for (i in 0..2) {
                for (j in 0..2) {
                    val cardView = (binding.cardsLayout[i] as ViewGroup)[j]
                    val position = (binding.cardsLayout.size * i) + j
                    initCard(cardView, position, randomCards[position])
                    Log.d("AnimatedCardsActivity", "shop name = ${randomCards[position].shopName}")
                }
            }
        }

        animatedCardsViewModel.getInputCards()
    }

    private fun initCard(cardView: View, position: Int, card: InputCard) {
        val shopNameTextView = cardView.findViewById<TextView>(R.id.nameCard)
        val cardImageView = cardView.findViewById<ImageView>(R.id.imageCard)
        val cardImageName = "card_${position + 1}"
        shopNameTextView.text = card.shopName
        cardImageView.setImageResource(resources.getIdentifier(cardImageName, "drawable", packageName))
    }

}