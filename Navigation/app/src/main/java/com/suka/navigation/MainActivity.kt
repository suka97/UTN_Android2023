package com.suka.navigation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.suka.navigation.entities.User

class MainActivity : AppCompatActivity() {

    private var newUser : User = User("Juan", "Perez", "andres@hotmail", "1234")

    // creo una lista, un array de tamaño dinamico
    private var users : MutableList<User> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // logeo en consola, el tag sirve para identificarlo entre todo el log
        Log.d("MainActivity", newUser.saludar())

        // agrego items a la lista
        users.add(User("user1","Perez","@asd","pass1"))
        users.add(User("user2","Perez","@asd","pass2"))
        users.add(User("user3","Perez","@asd","pass3"))
        users.add(User("user4","Perez","@asd","pass4"))

        users.forEach{ currentUser ->
            Log.d("MainActivity", currentUser.saludar())
        }
        users[1].name = "jose"
    }
}

