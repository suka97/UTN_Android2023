package com.suka.playerapp.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class Player (
    var id : Number,
    var name : String,
    var lastname : String,
    var team : String
) : Parcelable