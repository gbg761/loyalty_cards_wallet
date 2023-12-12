package com.example.loyaltycardwallet.ui.add

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.amulyakhare.textdrawable.TextDrawable
import com.example.loyaltycardwallet.databinding.ListItemAddCardBinding
import com.example.loyaltycardwallet.model.CardType
import com.example.loyaltycardwallet.model.InputCardWithType
import com.example.loyaltycardwallet.model.getFirstLettersFromShopName
import com.simplecityapps.recyclerview_fastscroll.views.FastScrollRecyclerView

class DefaultCardsAdapter :
    RecyclerView.Adapter<DefaultCardsAdapter.AddCardViewHolder>(),
    FastScrollRecyclerView.SectionedAdapter {

    private val cards: ArrayList<InputCardWithType> = arrayListOf()

    inner class AddCardViewHolder(val binding: ListItemAddCardBinding) : ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddCardViewHolder {
        val binding = ListItemAddCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AddCardViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AddCardViewHolder, position: Int) {
        val card = cards[position]
        with(holder) {
            when(card.type) {
                CardType.CARD_WITH_GROUP_NAME -> {
                    binding.groupNameTextView.text = card.groupLetter.toString()
                }
                CardType.CARD_WO_GROUP_NAME -> {
                    binding.shopNameTextView.text = card.shopName

                    val roundDrawable = TextDrawable.builder()
                        .beginConfig()
                        .bold()
                        .endConfig()
                        .buildRound(card.getFirstLettersFromShopName(), Color.parseColor(card.color))
                    binding.cardRoundImageView.setImageDrawable(roundDrawable)
                }
            }
        }
    }

    override fun getItemCount(): Int = cards.size

    override fun getSectionName(position: Int): String = cards[position].shopName[0].toString()

    fun updateList(inputCards: List<InputCardWithType>) {
        cards.clear()
        cards.addAll(inputCards)
        notifyDataSetChanged()
    }
}