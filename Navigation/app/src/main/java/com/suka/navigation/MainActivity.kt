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
        users.add(User("Pepito1","Perez","@asd","pass"))
        users.add(User("Pepito2","Perez","@asd","pass"))
        users.add(User("Pepito3","Perez","@asd","pass"))
        users.add(User("Pepito4","Perez","@asd","pass"))

        users.forEach{ currentUser ->
            Log.d("MainActivity", currentUser.saludar())
        }
        users[1].name = "jose"
    }
}

