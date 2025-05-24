import kotlinx.serialization.*
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.File

@Serializable
data class Player(
    var name: String,
    var balance: Float,
    var tier: Int,
    var ownedStands: MutableList<Stand>, // Mutable list used for future changes (for adding different stands).
){

    fun displayProfile(){
        val lemonadeStands = ownedStands.count { it.name == "Lemonade Stand";} // Loop through list and count players stands.
        val newspaperStands = ownedStands.count { it.name == "Newspaper Stand";}// ""
        val hotDogStands = ownedStands.count { it.name == "Hot Dog Stand";} // ""
        println("=======================================================================");
        println("                          ${name}'s Profile");
        println("=======================================================================");
        println("Balance = $${balance} | Rank = ${tier}")
        println("Lemonade Stands owned = $lemonadeStands");
        println("Newspaper Stands owned = $newspaperStands");
        println("Hot Dog Stands owned = $hotDogStands");
        println("Hit 'Enter' to Return to Main Menu");
        readLine();
    }

}
