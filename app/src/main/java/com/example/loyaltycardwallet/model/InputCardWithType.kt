package com.example.loyaltycardwallet.model

import com.example.loyaltycardwallet.utils.countWords

enum class CardType {
    CARD_WITH_GROUP_NAME,
    CARD_WO_GROUP_NAME
}

data class InputCardWithType(
    val groupLetter: Char?,
    val shopName: String,
    val cardName: String,
    val color: String,
    val type: CardType
)

// возвращает строку для помещения в аватар карты
// если название магазина состоит из 2х и более слов,
// берем первые буквы первых двух слов,
// если название магазина состоит из 1 слова,
// возаращем 1ую букву названия
fun InputCardWithType.getFirstLettersFromShopName(): String {
    // создает строку из букв, содержащихся в названии магазина
    // для отображения в "аватаре" карты
    val strBuilder = StringBuilder(2)
    // определяем количество слов в названии магазина
    val cnt = shopName.countWords()
    strBuilder.append(shopName[0])
    // Если слов в названии магазина > 1, то берем первые буквы первых 2х слов
    // иначе берем первые 2 буквы названия
    var i = 1
    if (cnt > 1) {
        while (shopName[i] != ' ') i++
        strBuilder.append(shopName[i + 1])
    } else {
        // пока не наткнемся на цифру или букву
        while (!(shopName[i].isLetterOrDigit())) i++
        strBuilder.append(shopName[i])
    }
    return strBuilder.toString()
}