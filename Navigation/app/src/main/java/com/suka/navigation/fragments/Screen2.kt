package com.suka.navigation.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import com.suka.navigation.R

class Screen2 : Fragment() {

    lateinit var btnNavigate : Button

    lateinit var v : View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.fragment_screen2, container, false)

        btnNavigate = v.findViewById(R.id.button)

        btnNavigate.setOnClickListener{
            findNavController().navigateUp()    // es lo mismo que tocar flecha atras
        }

        return v
    }

}