import kotlinx.serialization.*
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.File

@Serializable
data class Stand(
    val name: String,
    val cost: Float,
    val income: Float,
)
