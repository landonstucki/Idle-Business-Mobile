// screens.kt
// Entry point for Compose UI screens in the Idle Business Game app.
package com.example.idlebusinessgame

// Import Jetpack Compose and UI toolkit dependencies.
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.idlebusinessgame.R
import com.example.idlebusinessgame.Player
// Instantiate managers for shop and work logic.
private val shop = ShopManager()
private val work = WorkManager()

// Main menu screen composable displaying background and menu buttons.
@Composable
fun MainMenuScreen(onPlayClick: () -> Unit) {
    // Track whether the instructions dialog is visible.
    var showDialog by remember { mutableStateOf(false) }

    Box(modifier = Modifier.fillMaxSize()) {
        // Background image filling the entire screen.
        Image(
            painter = painterResource(id = R.drawable.menu_background),
            contentDescription = "Main menu background",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.fillMaxSize()
        )

        // Container for Play and Instructions buttons, aligned at bottom center.
        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 80.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Play button to start the game.
            Image(
                painter = painterResource(id = R.drawable.playbutton),
                contentDescription = "Play Button",
                modifier = Modifier
                    .size(width = 150.dp, height = 72.dp)
                    .clickable { onPlayClick() }
            )

            // Spacer between Play and Instructions buttons.
            Spacer(modifier = Modifier.height(20.dp))

            // Instructions button to show the How to Play dialog.
            Image(
                painter = painterResource(id = R.drawable.instructionsbutton),
                contentDescription = "Instructions Button",
                modifier = Modifier
                    .size(width = 220.dp, height = 72.dp)
                    .clickable { showDialog = true }
            )
        }

        // Conditional display of the instructions dialog.
        if (showDialog) {
            AlertDialog(
                onDismissRequest = { showDialog = false },
                title = { Text("How to Play") },
                text = {
                    // Instruction text with steps to play the game.
                    Text(
                        "1. Tap on PLAY to get started!\n" +
                                "2. Head to the SHOP to purchase a stand.\n" +
                                "3. Start working by tapping the WORK button!\n" +
                                "4. Use your money to buy more stands and invest in better ones.\n" +
                                "5. Become a Millionaire."
                    )
                },
                confirmButton = {
                    // Close button for the dialog.
                    TextButton(onClick = { showDialog = false }) {
                        Text("Close")
                    }
                }
            )
        }
    }
}

// Play screen composable showing game UI elements: work button, profile, shop, and stands.
@Composable
fun PlayScreen(
    player: Player,
    onPlayClick: () -> Unit
) {
    // Track whether the shop overlay is visible.
    var showShop by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            // Vertical gradient background from light to gold tone.
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFFF5F5F5),
                        Color(0xFFD19F39)
                    )
                )
            )
    ) {
        // WORK button container and click handler.
        Box(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(start = 250.dp, top = 400.dp)
                .wrapContentSize()
                .clickable {
                    work.performWork(player)
                }
        ) {
            // Work button image.
            Image(
                painter = painterResource(id = R.drawable.workbutton),
                contentDescription = "Work Button",
                modifier = Modifier
                    .width(100.dp)
                    .height(200.dp)
            )
        }

        // Title graphic at top center.
        Image(
            painter = painterResource(id = R.drawable.idlebiztextpic),
            contentDescription = "Title graphic",
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(start = 16.dp, top = 0.dp)
                .size(200.dp)
        )

        // Profile picture at top-left area.
        Image(
            painter = painterResource(id = R.drawable.maleprofile),
            contentDescription = "Profile Picture",
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(start = 16.dp, top = 150.dp)
                .size(72.dp)
        )

        // SHOP button at bottom-right to open the shop.
        Image(
            painter = painterResource(id = R.drawable.shoppingbutton),
            contentDescription = "Shopping Cart",
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(bottom = 50.dp, end = 25.dp)
                .size(125.dp)
                .clickable { showShop = true }
        )

        // Display the player's current balance with two decimal formatting.
        Text(
            text = "Balance: \$${"%.2f".format(player.balance)}",
            style = MaterialTheme.typography.bodyLarge,
            color = Color.Black,
            modifier = Modifier
                .wrapContentSize()
                .align(Alignment.TopCenter)
                .padding(top = 175.dp)
        )

        // Header for owned stands list.
        Text(
            text = "Owned Business Stands",
            style = MaterialTheme.typography.bodyLarge,
            color = Color.Black,
            fontSize = 25.sp,
            textDecoration = TextDecoration.Underline,
            modifier = Modifier
                .wrapContentSize()
                .align(Alignment.TopCenter)
                .padding(top = 240.dp)
        )

        // HOME button at bottom-left to return to main menu.
        Image(
            painter = painterResource(id = R.drawable.homebutton),
            contentDescription = "Home Button",
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(start = 16.dp, bottom = 75.dp)
                .size(75.dp)
                .clickable { onPlayClick() }
        )

        // Column listing each stand type owned by the player.
        Column(
            modifier = Modifier
                .padding(start = 16.dp, top = 350.dp),
            verticalArrangement = Arrangement.spacedBy(60.dp)
        ) {
            // Lemonade Stand row: count and display price per click.
            val ownedLemonadeCount = player.ownedStands.count { it.name == "Lemonade Stand" }
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(id = R.drawable.lemonadepic),
                    contentDescription = "Lemonade Stand",
                    modifier = Modifier.size(60.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Column {
                    Text(
                        text = "Lemonade",
                        fontSize = 18.sp,
                        textDecoration = TextDecoration.Underline
                    )
                    Text(
                        text = "Owned x$ownedLemonadeCount | \$0.25 ea",
                        fontSize = 14.sp,
                        color = Color.Gray
                    )
                }
            }

            // Newspaper Stand row: count and display price per click.
            val ownedNewspaperCount = player.ownedStands.count { it.name == "Newspaper Stand" }
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(id = R.drawable.newspaperpic),
                    contentDescription = "Newspaper Stand",
                    modifier = Modifier.size(60.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Column {
                    Text(
                        text = "Newspaper",
                        fontSize = 18.sp,
                        textDecoration = TextDecoration.Underline
                    )
                    Text(
                        text = "Owned x$ownedNewspaperCount | \$2.00 ea",
                        fontSize = 14.sp,
                        color = Color.Gray
                    )
                }
            }

            // Hot Dog Stand row: count and display price per click.
            val ownedHotDogCount = player.ownedStands.count { it.name == "Hot Dog Stand" }
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(id = R.drawable.hotdogpic),
                    contentDescription = "Hot Dog Stand",
                    modifier = Modifier.size(60.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Column {
                    Text(
                        text = "Hot Dog",
                        fontSize = 18.sp,
                        textDecoration = TextDecoration.Underline
                    )
                    Text(
                        text = "Owned x$ownedHotDogCount | \$5.00 ea",
                        fontSize = 14.sp,
                        color = Color.Gray
                    )
                }
            }
        }

        // Display the shop overlay when requested.
        if (showShop) {
            ShopScreen(onClose = { showShop = false }, player = player)
        }
    }
}

// Shop overlay screen composable with purchase options.
@Composable
fun ShopScreen(
    onClose: () -> Unit,
    player: Player
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            // Semi-transparent black overlay behind the shop dialog.
            .background(Color.Black.copy(alpha = 0.5f))
    ) {
        // Centered dialog container for shop contents.
        Box(
            modifier = Modifier
                .align(Alignment.Center)
                .width(400.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(Color.White)
                .padding(24.dp)
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                // Shop title header.
                Text(
                    text = "SHOP",
                    fontSize = 30.sp,
                    color = Color.Black,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )

                // NEW BALANCE DISPLAY: show current player balance at top of shop.
                Text(
                    text = "Balance: \$${"%.2f".format(player.balance)}",
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color.Black,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Lemonade Stand purchase section.
                Column {
                    // 1) Create stand instance
                    val lemonadeStand = Stand(
                        name   = "Lemonade Stand",
                        cost   = 50f,
                        income = 0.25f
                    )
                    // 2) Count how many are owned
                    val ownedLemonadeCount = player.ownedStands.count { it.name == "Lemonade Stand" }
                    // 3) Display icon and name row
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Image(
                            painter            = painterResource(id = R.drawable.lemonadepic),
                            contentDescription = "Lemonade",
                            modifier           = Modifier.size(48.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(text = "Lemonade Stand - \$${lemonadeStand.cost}", fontSize = 18.sp)
                    }
                    Spacer(modifier = Modifier.height(4.dp))
                    // 4) Show owned count, income rate, and Buy button
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier          = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text     = "Owned x$ownedLemonadeCount | Income: \$${lemonadeStand.income}/ea",
                            color    = Color.Gray,
                            fontSize = 14.sp,
                            modifier = Modifier.weight(1f)
                        )
                        Button(
                            onClick  = { shop.purchaseStand(player, lemonadeStand) },
                            modifier = Modifier.height(36.dp)
                        ) {
                            Text("Buy", fontSize = 14.sp)
                        }
                    }
                }

                // Newspaper Stand purchase section.
                val newspaperStand = Stand(
                    name   = "Newspaper Stand",
                    cost   = 300f,
                    income = 2.00f
                )
                val ownedNewspaperCount2 = player.ownedStands.count { it.name == "Newspaper Stand" }
                Column {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Image(
                            painter            = painterResource(id = R.drawable.newspaperpic),
                            contentDescription = "Newspaper",
                            modifier           = Modifier.size(48.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(text = "Newspaper Stand - \$${newspaperStand.cost}", fontSize = 18.sp)
                    }
                    Spacer(modifier = Modifier.height(4.dp))
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier          = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text     = "Owned x$ownedNewspaperCount2 | Income: \$${newspaperStand.income}/ea",
                            color    = Color.Gray,
                            fontSize = 14.sp,
                            modifier = Modifier.weight(1f)
                        )
                        Button(
                            onClick  = { shop.purchaseStand(player, newspaperStand) },
                            modifier = Modifier.height(36.dp)
                        ) {
                            Text("Buy", fontSize = 14.sp)
                        }
                    }
                }

                // Hot Dog Stand purchase section.
                val hotDogStand = Stand(
                    name   = "Hot Dog Stand",
                    cost   = 750f,
                    income = 5.00f
                )
                val ownedHotDogCount2 = player.ownedStands.count { it.name == "Hot Dog Stand" }
                Column {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Image(
                            painter            = painterResource(id = R.drawable.hotdogpic),
                            contentDescription = "Hot Dog",
                            modifier           = Modifier.size(48.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(text = "Hot Dog Stand - \$${hotDogStand.cost}", fontSize = 18.sp)
                    }
                    Spacer(modifier = Modifier.height(4.dp))
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier          = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text     = "Owned x$ownedHotDogCount2 | Income: \$${hotDogStand.income}/ea",
                            color    = Color.Gray,
                            fontSize = 14.sp,
                            modifier = Modifier.weight(1f)
                        )
                        Button(
                            onClick  = { shop.purchaseStand(player, hotDogStand) },
                            modifier = Modifier.height(36.dp)
                        ) {
                            Text("Buy", fontSize = 14.sp)
                        }
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                // Close button to dismiss the shop overlay.
                Button(onClick = onClose, modifier = Modifier.align(Alignment.End)) {
                    Text(text = "Close")
                }
            }
        }
    }
}
