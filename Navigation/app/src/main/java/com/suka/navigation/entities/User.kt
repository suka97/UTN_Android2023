package com.suka.navigation.entities

import com.suka.navigation.entities.Saludo

class User(
    var name: String,
    var lastname: String,
    var email: String,
    var password: String,
) : Saludo {

    // override es que esta implementando el metodo de la interfaz Saludo
    override fun saludar(): String {
        return "hola me llamo $name $lastname"
    }
}