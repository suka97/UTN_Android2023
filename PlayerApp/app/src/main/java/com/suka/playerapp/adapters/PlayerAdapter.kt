package com.suka.playerapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.suka.playerapp.R
import com.suka.playerapp.entities.Player

class PlayerAdapter(
    var playerList : MutableList<Player>,
    var onItemClick : (Int) -> Unit
) : RecyclerView.Adapter<PlayerAdapter.PlayerHolder>() {

    class PlayerHolder (v: View) : RecyclerView.ViewHolder(v) {
        private var view: View
        init {
            this.view = v
        }

        fun setName (name : String) {
            var txtName : TextView = view.findViewById(R.id.txtName)
            txtName.text = name
        }
        fun setTeam (team : String) {
            var txtTeam : TextView = view.findViewById(R.id.txtTeam)
            txtTeam.text = team
        }

        fun getCard() : CardView {
            return view.findViewById(R.id.cardPlayer)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_player, parent, false)
        return (PlayerHolder(view))
    }

    override fun getItemCount(): Int {
        return playerList.size
    }

    override fun onBindViewHolder(holder: PlayerHolder, position: Int) {
        holder.setName(playerList[position].name)
        holder.setTeam(playerList[position].team)

        holder.getCard().setOnClickListener() {
            onItemClick(position)
        }
    }
}