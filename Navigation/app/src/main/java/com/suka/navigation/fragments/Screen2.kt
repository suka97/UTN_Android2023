package com.suka.navigation.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import com.suka.navigation.R
import com.suka.navigation.entities.User

class Screen2 : Fragment() {

    lateinit var btnNavigate : Button

    lateinit var v : View

    var argText : String? = null
    var argUser : User? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.fragment_screen2, container, false)

        btnNavigate = v.findViewById(R.id.button)

        btnNavigate.setOnClickListener{
            findNavController().navigateUp()    // es lo mismo que tocar flecha atras
        }

        argText = Screen2Args.fromBundle(requireArguments()).textArg
        argUser = Screen2Args.fromBundle(requireArguments()).user

        return v
    }


    override fun onStart() {
        super.onStart()
    }

}