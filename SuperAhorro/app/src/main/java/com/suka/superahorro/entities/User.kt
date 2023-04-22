package com.suka.superahorro.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class User (
    var mail : String,
    var pass : String,
    var picture_url : String
) : Parcelable