class Work {

    fun workMenu(player: Player) {
        var working = true
        while (working) {
            // Declaring number count of how many stands of each type are owned.
            val lemonadeCount = player.ownedStands.count { it.name == "Lemonade Stand" }
            val newspaperCount = player.ownedStands.count { it.name == "Newspaper Stand" }
            val hotDogCount = player.ownedStands.count { it.name == "Hot Dog Stand" }
            println("\n=======================================================================")
            println("                        WORK (Balance: $ ${player.balance})")
            println("=======================================================================\n")
            println("Press 'W' to start working!")
            println("[Q] To return to the Main Menu")
            val answer = readLine()!!.lowercase()
            if (answer == "w") {
                // Lemonade owned only
                if (lemonadeCount >= 1) {
                    print("[${player.name}] -> Selling a fresh cup of Lemonade")
                    if (newspaperCount >= 1){
                        print(" and the current newspaper")
                    }
                    if (hotDogCount >= 1){
                        print(", with a delicious all American Hot Dog.")
                    }
                }

                val lemonadeCost = player.ownedStands
                    .firstOrNull { it.name == "Lemonade Stand" }
                    ?.income ?: 0f
                val newspaperCost = player.ownedStands
                    .firstOrNull { it.name == "Newspaper Stand" }
                    ?.income ?: 0f
                val hotDogCost = player.ownedStands
                    .firstOrNull { it.name == "Hot Dog Stand" }
                    ?.income ?: 0f

                val lemonadeProfit = lemonadeCount * lemonadeCost
                val newspaperProfit = newspaperCount * newspaperCost
                val hotDogProfit = hotDogCount * hotDogCost
                val totalProfit = lemonadeProfit + newspaperProfit + hotDogProfit
                player.balance += totalProfit
                Thread.sleep(1000)
                if (lemonadeCount >= 1){
                    print("\nLemonade Stands: +$${lemonadeProfit} ")
                }
                if (newspaperCount >= 1){
                    print("Newspaper Stands: +$${newspaperProfit} ")
                }
                if (hotDogCount >= 1){
                    print("Hot Dog Stands: +$${hotDogProfit} ")
                }
                if (player.ownedStands.count() == 0){
                    println("You should probably visit the store and purchase a stand.")
                }

            } else if (answer == "q") {
                working = false
            }

        }
    }
}