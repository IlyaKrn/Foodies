package com.ilyakrn.foodies.ui

//получение строки цены из копеек
fun getPriceFromInt(price: Int): String{
    val rubles = price / 100
    val cops = price % 100
    val copsStr = if(cops > 9) ",$cops" else ",0$cops"
    val strCopsFin = if(copsStr == ",00") "" else copsStr
    return "${rubles}${strCopsFin} ₽"
}