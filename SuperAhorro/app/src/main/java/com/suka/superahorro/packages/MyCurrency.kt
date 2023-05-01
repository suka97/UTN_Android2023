package com.suka.superahorro.packages

import java.text.NumberFormat
import java.util.*

fun Number?.toCurrency() : String {
    val formatter = NumberFormat.getCurrencyInstance(Locale.US)
    return formatter.format(this?: 0F)
}