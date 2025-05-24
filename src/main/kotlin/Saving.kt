import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.File

class Saving {
    fun saveGame(player: Player) {
        val folder = File("saves")
        if (!folder.exists()) folder.mkdir() // Create new variable with folder unless it already exists. then set path to folder.

        val saveFile = File(folder, "${player.name}.json") // Create file in folder with players user set as file.
        saveFile.writeText(Json.encodeToString(player)) // Turn string into json.
    }
    fun loadGame(name: String): Player {
        val file = File("saves/$name.json") // Store json into file.
        val json = file.readText() // Read Json and store into json.
        return Json.decodeFromString<Player>(json) // Turn json into string.
    }
}