package com.suka.playerapp.entities

class PlayerRepository {
    var players = mutableListOf<Player>()

    init {
        players.add(Player(1, "Nombre1", "Apellido1", "Team1"))
        players.add(Player(2, "Nombre2", "Apellido2", "Team2"))
        players.add(Player(3, "Nombre3", "Apellido3", "Team3"))
        players.add(Player(4, "Nombre4", "Apellido4", "Team4"))
        players.add(Player(5, "Nombre1", "Apellido1", "Team5"))
        players.add(Player(6, "Nombre2", "Apellido2", "Team6"))
        players.add(Player(7, "Nombre3", "Apellido3", "Team7"))
        players.add(Player(8, "Nombre4", "Apellido4", "Team8"))
        players.add(Player(9, "Nombre1", "Apellido1", "Team9"))
        players.add(Player(10, "Nombre2", "Apellido2", "Team10"))
        players.add(Player(11, "Nombre3", "Apellido3", "Team11"))
        players.add(Player(12, "Nombre4", "Apellido4", "Team12"))
    }
}