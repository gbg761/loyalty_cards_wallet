package com.example.loyaltycardwallet.data

import com.example.loyaltycardwallet.data.source.InputCardEntity
import com.example.loyaltycardwallet.model.InputCard

fun InputCard.toLocal() = InputCardEntity(
    id = id,
    shopName = shopName,
    cardName = cardName,
    color = color
)

fun List<InputCard>.toLocal() = map(InputCard::toLocal)

fun InputCardEntity.toExternal() = InputCard(
    id = id,
    shopName = shopName,
    cardName = cardName,
    color = color
)

@JvmName("localToExternal")
fun List<InputCardEntity>.toExternal() = map(InputCardEntity::toExternal)