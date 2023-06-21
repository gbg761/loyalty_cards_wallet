package com.example.loyaltycardwallet.data.source

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class InputCardEntity(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "shop_name") val shopName: String,
    @ColumnInfo(name = "card_name") val cardName: String,
    @ColumnInfo(name = "color") val color: String
)