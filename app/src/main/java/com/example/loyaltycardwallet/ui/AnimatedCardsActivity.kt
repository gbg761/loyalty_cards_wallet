package com.example.loyaltycardwallet.ui

import android.animation.AnimatorSet
import android.animation.ValueAnimator
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.forEach
import androidx.core.view.forEachIndexed
import androidx.core.view.get
import androidx.core.view.size
import androidx.lifecycle.ViewModelProvider
import com.example.loyaltycardwallet.App
import com.example.loyaltycardwallet.R
import com.example.loyaltycardwallet.databinding.ActivityAnimatedCardsBinding
import com.example.loyaltycardwallet.di.AppComponent
import com.example.loyaltycardwallet.model.InputCard
import com.example.loyaltycardwallet.ui.add.AddCardActivity
import com.example.loyaltycardwallet.utils.dpToPx
import com.example.loyaltycardwallet.utils.getScreenWidth

const val DEFAULT_ANIMATION_DURATION = 4_000L

class AnimatedCardsActivity : AppCompatActivity() {

    private lateinit var appComponent: AppComponent
    private lateinit var binding: ActivityAnimatedCardsBinding

    private lateinit var animatedCardsViewModel: AnimatedCardsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAnimatedCardsBinding.inflate(layoutInflater)
        val rootView = binding.root
        setContentView(rootView)

        supportActionBar?.setDisplayShowTitleEnabled(false)

        appComponent = (application as App).appComponent
        val factory = appComponent.getAnimatedCardsViewModelFactory()
        animatedCardsViewModel = ViewModelProvider(this, factory)[AnimatedCardsViewModel::class.java]

        animatedCardsViewModel.inputCards.observe(this) { randomCards: List<InputCard> ->
            for (i in 0..2) {
                for (j in 0..2) {
                    val cardView = (binding.cardsLayout[i] as ViewGroup)[j]
                    val position = (binding.cardsLayout.size * i) + j
                    initCard(cardView, position, randomCards[position])
                }
            }

            // layout width is 3 cards of 150dp + the distance between the cards (2 * 10 dp) + screen offset (5dp)
            val layoutWidth = 475 // in dp

            // x1 and x2 set the X coordinate positions between which the animation will take place
            val x1 = (layoutWidth.dpToPx() - getScreenWidth()).toFloat()
            val x2 = 5.dpToPx().toFloat()
            startAnimation(x1, x2)
        }

        binding.addCardBtn.setOnClickListener {
            val intent = Intent(this, AddCardActivity::class.java)
            startActivity(intent)
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

    private fun startAnimation(x1: Float, x2: Float) {
        val animators = mutableListOf<ValueAnimator>()
        binding.cardsLayout.forEachIndexed { index: Int, row: View ->
            val animator = if ((index + 1) % 2 != 0) { // animate 1st and 3rd rows
                initRowAnimator(row as ViewGroup, -x1, x2)
            } else { // animation 2nd row
                initRowAnimator(row as ViewGroup, x2, -x1)
            }
            animators.add(animator)
        }
        val animatorSet = AnimatorSet()
        animatorSet.playTogether(animators.toList())
        animatorSet.duration = DEFAULT_ANIMATION_DURATION
        animatorSet.interpolator = AccelerateDecelerateInterpolator()
        animatorSet.start()
    }

    // init ValueAnimator for rows
    private fun initRowAnimator(row: ViewGroup, start: Float, end: Float): ValueAnimator {
        val animator = ValueAnimator.ofFloat(start, end)
        animator.addUpdateListener {
            val value = it.animatedValue as Float
            animateRow(row, value)
        }
        animator.repeatCount = ValueAnimator.INFINITE
        animator.repeatMode = ValueAnimator.REVERSE
        return animator
    }

    private fun animateRow(row: ViewGroup, value: Float) {
        row.forEach { cardView: View ->
            cardView.translationX = value
        }
    }

    fun onCardClick(view: View) {
        val shopName = view.findViewById<TextView>(R.id.nameCard).text
        Toast.makeText(this, "shop name = $shopName", Toast.LENGTH_SHORT).show()
    }
}