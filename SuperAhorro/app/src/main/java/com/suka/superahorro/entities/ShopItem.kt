package com.suka.superahorro.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class ShopItem (
    var id : Number,
    var name : String,
    var price : Number,
    var amount : Number,
    var picture_url : String? = null
) : Parcelable