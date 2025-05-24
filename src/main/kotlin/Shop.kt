import com.sun.tools.javac.Main

class Shop() {
    fun displayShopInfo(player: Player){
        println("=======================================================================");
        println("                        SHOP (Balance: $ ${player.balance})");
        println("=======================================================================");

        println("[L] Purchase x1 Lemonade Stand = $50");
        println("[N] Purchase x1 Newspaper Stand = $300");
        println("[H] Purchase x1 Hot-Dog Stand = $750");
        println("\n[Q] Return to Main Menu");
    }
    fun purchaseStand(player: Player, stand: Stand){
        if (player.balance < stand.cost){ // Make sure player can afford the stand.
            println("[Transaction Failed] -> You cannot afford to purchase a ${stand.name}!");
        }
        else{
            player.balance -= stand.cost; // If player has enough money, complete transaction.
            player.ownedStands.add(stand);
            println("\n\n      ------> You purchased a ${stand.name} for $${stand.cost}! <------");
        }
    }
}