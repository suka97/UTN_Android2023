package com.suka.navigation.entities

import android.os.Parcelable
import com.suka.navigation.entities.Saludo
import kotlinx.parcelize.Parcelize

@Parcelize
class User(
    var name: String,
    var lastname: String,
    var email: String,
    var password: String,
) : Saludo, Parcelable {

    // override es que esta implementando el metodo de la interfaz Saludo
    override fun saludar(): String {
        return "hola me llamo $name $lastname"
    }
}