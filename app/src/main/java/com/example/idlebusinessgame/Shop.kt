package com.example.idlebusinessgame

class ShopManager {
    fun purchaseStand(player: Player, stand: Stand) {
        if (player.balance >= stand.cost) {
            player.balance -= stand.cost
            player.ownedStands.add(stand)
        }
    }
}
