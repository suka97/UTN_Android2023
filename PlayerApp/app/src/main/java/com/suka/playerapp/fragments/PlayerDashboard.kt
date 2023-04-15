package com.suka.playerapp.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.suka.playerapp.R
import com.suka.playerapp.adapters.PlayerAdapter
import com.suka.playerapp.entities.Player
import com.suka.playerapp.entities.PlayerRepository

class PlayerDashboard : Fragment() {

    lateinit var v : View
    lateinit var recPlayer : RecyclerView

    lateinit var adapter : PlayerAdapter

    var playersRepository : PlayerRepository = PlayerRepository()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.fragment_player_dashboard, container, false)
        recPlayer = v.findViewById(R.id.recPlayer)

        return v
    }


    override fun onStart() {
        super.onStart()

        adapter = PlayerAdapter(playersRepository.players) { position ->
            val action = PlayerDashboardDirections.actionPlayerDashboardToPlayerDetail(playersRepository.players[position])
            findNavController().navigate(action)
        }
        recPlayer.layoutManager = LinearLayoutManager(context)
        recPlayer.adapter = adapter
    }


}