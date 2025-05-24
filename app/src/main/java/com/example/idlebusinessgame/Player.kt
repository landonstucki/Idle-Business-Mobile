// Player.kt
package com.example.idlebusinessgame

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

class Player(
    name: String = "Player",
    initialBalance: Float = 50f,
    tier: Int = 1,
    initialStands: List<Stand> = emptyList()
) {
    var name by mutableStateOf(name)
    var balance by mutableFloatStateOf(initialBalance)      // Float state
    var tier by mutableStateOf(tier)
    val ownedStands = mutableStateListOf<Stand>().apply {   // observable list
        addAll(initialStands)
    }
}
