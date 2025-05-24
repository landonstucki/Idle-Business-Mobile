package com.example.idlebusinessgame

class WorkManager {
    fun performWork(player: Player) {
        val totalProfit = player.ownedStands.sumOf { it.income.toDouble() }.toFloat()
        player.balance += totalProfit
    }
}
