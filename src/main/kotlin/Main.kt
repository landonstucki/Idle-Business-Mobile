import kotlinx.serialization.*
import java.io.File

fun main() {
    // Welcome section for game.
    println("=======================================================================");
    println("                          IDLE BUSINESS");
    println("=======================================================================");
    // Create blank variable playerName for username placeholder.
    var playerName:String;
    lateinit var player: Player; // Player blank declaration (allows for user later lateinit).
    var save: Saving = Saving();
    while (true) {
        println("- If creating a new account, please enter your name or a distinct username -")
        println("- If logging into an existing account, please enter your name or a distinct username -")
        print("Username: ");
        val input = readLine() ?: "Player"
        val file = File("saves/$input.json") // Set file to the users input (username).

        if (file.exists()) { // If the username already exists in the file, print error and reloop.
            println("Welcome back $input, let me load in your account details...")
            Thread.sleep(3000);
            println("Load successful");
            playerName = input
            player = save.loadGame(playerName); // Run loadGame function (store json string text into player).
            break
        } else {
            println("Creating new account for $input please wait...");
            Thread.sleep(3000);
            println("Account creation successful");
            println("\nWelcome $input")

            playerName = input
            player = Player(name = playerName, balance = 50f, tier = 0, ownedStands = mutableListOf()) // Create new player.
            break
        }
    }

    var gameRunning = true // Boolean declaration for check if game is running.
    val shop = Shop()
    val work = Work()
    // Idle Business Game Loop Declaration.
    while (gameRunning) {
        // Main Menu
        println("\n=======================================================================");
        println("                          MAIN MENU");
        println("=======================================================================");
        println("1. Start Working!");
        println("2. Check ${player.name}'s profile");
        println("3. Shop");
        println("4. Instructions");
        println("9. SAVE and quit");
        // Convert to Integer for if-statements with Error Handling.
        var menuAnswer = 0; // Variable to reassign
        try {
            menuAnswer = readLine()!!.toInt()
        }
        catch(e: Exception){
            println("[Error] -> Invalid choice, please choose a number.");
        }

        // Main Menu Handling
        // [1] WORKING.
        if (menuAnswer == 1){
            work.workMenu(player);
        }
        // [2] CHECKING PROFILE.
        else if (menuAnswer == 2){
            // Open up the players profile.
            player.displayProfile();
        }
        // [3] SHOPPING.
        else if (menuAnswer == 3){
            var shopping = true;
            while (shopping) {
                // Display the Shop Menu.
                shop.displayShopInfo(player);
                val checkout = readLine()!!.lowercase();
                // Purchase a lemonade stand.
                if (checkout == "l") {
                    val lemonadeStand = Stand(name = "Lemonade Stand", cost = 50f, income = .25f);
                    shop.purchaseStand(player, lemonadeStand);
                }
                // Purchase a Newspaper stand.
                if (checkout == "n") {
                    val newspaperStand = Stand(name = "Newspaper Stand", cost = 300f, income = 2.00f);
                    shop.purchaseStand(player, newspaperStand);
                }
                // Purchase a Hot Dog stand.
                if (checkout == "h") {
                    val hotDogStand = Stand(name = "Hot Dog Stand", cost = 750f, income = 5.00f);
                    shop.purchaseStand(player, hotDogStand);
                }
                // Return to main menu.
                if (checkout == "q"){
                    shopping = false;
                }

            }
        }
        else if (menuAnswer == 4){
            println("=======================================================================")
            println("                            INSTRUCTIONS")
            println("=======================================================================")
            println("1. Earn money by choosing 'Start Working'.")
            println("2. Use money to buy stands in the Shop.")
            println("3. Each stand earns you more income when you work.")
            println("4. Check your profile to see how rich you're becoming.")
            println("5. Don't go bankrupt. Or do. We won't judge.")
            println("Press Enter to return to the main menu.")
            readLine();
        }
        // [9] EXIT.
        else if (menuAnswer == 9){
            gameRunning = false
        }
        // Game loop for Idle Business game.
        // println("Players Balance: ${player.balance}"); // Check Player Balance
    }
    // Goodbye.
    println("Thank you for playing our Idle Business Game, The game will now close");
    save.saveGame(player);
}