package com.example.idlebusinessgame

class ShopManager {
    val availableStands = listOf(
        Stand("Lemonade Stand", 10f, 1f),
        Stand("Newspaper Stand", 50f, 3f),
        Stand("Hotdog Stand", 100f, 5f)
    )

    fun purchaseStand(player: Player, stand: Stand) {
        if (player.balance >= stand.cost) {
            player.balance -= stand.cost
            player.ownedStands.add(stand)
        }
    }
}
