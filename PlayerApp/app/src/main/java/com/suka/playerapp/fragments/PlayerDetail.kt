package com.suka.playerapp.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.suka.playerapp.R
import com.suka.playerapp.entities.Player

class PlayerDetail : Fragment() {

    lateinit var v : View

    lateinit var txtName :  TextView
    lateinit var txtTeam : TextView

    lateinit var playerArg : Player

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.fragment_player_detail, container, false)

        // arguments
        playerArg = PlayerDetailArgs.fromBundle(requireArguments()).player

        // view objects
        txtName = v.findViewById(R.id.txtName)
        txtTeam = v.findViewById(R.id.txtTeam)

        txtName.setText(playerArg.name)
        txtTeam.setText(playerArg.team)

        return v
    }

}