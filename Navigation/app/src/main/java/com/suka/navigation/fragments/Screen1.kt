package com.suka.navigation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.suka.navigation.R
import com.suka.navigation.entities.User

class Screen1 : Fragment() {

    lateinit var label : TextView
    lateinit var btnNavigate : Button
    lateinit var inputUser : EditText
    lateinit var inputPass : EditText

    lateinit var v : View

    var users : MutableList<User> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.fragment_screen1, container, false)

        label = v.findViewById(R.id.txtLabel)
        btnNavigate = v.findViewById(R.id.button)
        inputUser = v.findViewById(R.id.input_user)
        inputPass = v.findViewById(R.id.input_pass)

        users.add(User("Name1", "Lastname1", "mail1", "pass1"))
        users.add(User("Name2", "Lastname2", "mail2", "pass2"))
        users.add(User("Name3", "Lastname3", "mail3", "pass3"))
        users.add(User("Name4", "Lastname4", "mail4", "pass4"))

        return v
    }

    override fun onStart() {
        super.onStart()

        // por poner un ejemplo ejecuto esto aca, lo podria hacer dentro del onCreate
        btnNavigate.setOnClickListener{
            val user : String = inputUser.text.toString()
            val pass : String = inputPass.text.toString()
            if ( user.isEmpty() || pass.isEmpty() ) {
                Snackbar.make(v,"Debe completar los campos", Snackbar.LENGTH_SHORT).show()
            }

            val user_match = users.find { it.email==user && it.password==pass }
            if ( user_match != null ) {
                val action = Screen1Directions.actionScreen1ToScreen2("mi string", user_match)

                findNavController().navigate(action)
            }
            else {
                Snackbar.make(v,"Usuario o contraseña incorrectos", Snackbar.LENGTH_SHORT).show()
            }
        }
    }

    // limpio los inputs
    override fun onResume() {
        super.onResume()

        inputUser.setText("")
        inputPass.setText("")
    }

}