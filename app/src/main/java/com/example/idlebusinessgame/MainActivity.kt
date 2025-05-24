package com.example.idlebusinessgame

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.remember
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // Create the one-and-only Player *inside* composition and remember it
            val player = remember {
                Player(
                    name = "Landon",
                    initialBalance = 50f,
                    tier = 1
                )
            }

            val navController = rememberNavController()
            MaterialTheme {
                NavHost(
                    navController = navController,
                    startDestination = "main_menu"
                ) {
                    composable("main_menu") {
                        MainMenuScreen(
                            onPlayClick = {
                                navController.navigate("play_screen")
                            }
                        )
                    }
                    composable("play_screen") {
                        PlayScreen(
                            player     = player,
                            onPlayClick = { navController.popBackStack() }
                        )
                    }
                }
            }
        }
    }
}
