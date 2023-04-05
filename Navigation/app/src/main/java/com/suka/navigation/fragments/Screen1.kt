package com.suka.navigation.fragments

import androidx.lifecycle.ViewModelProvider
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

class Screen1 : Fragment() {

    lateinit var label : TextView
    lateinit var btnNavigate : Button
    lateinit var input : EditText

    lateinit var v : View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.fragment_screen1, container, false)

        label = v.findViewById(R.id.txtLabel)
        btnNavigate = v.findViewById(R.id.button)
        input = v.findViewById(R.id.input)

        return v
    }

    override fun onStart() {
        super.onStart()

        // por poner un ejemplo ejecuto esto aca, lo podria hacer dentro del onCreate
        btnNavigate.setOnClickListener{
            val action = Screen1Directions.actionScreen1ToScreen2()
            findNavController().navigate(action)

            val inputText : String = input.text.toString()
            if ( inputText.isEmpty() ) {
                Snackbar.make(v,"Ingrese algo", Snackbar.LENGTH_SHORT).show()
            }
        }
    }

}