package com.mego.mycrypto.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Redeem
import androidx.compose.material.icons.outlined.Send
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen (val title:String, val icon:ImageVector, val route:String) {
    object Home: Screen("Home", Icons.Outlined.Home, "Home")
    object Rewards: Screen("Rewards", Icons.Outlined.Redeem, "Rewards")
    object Profile: Screen("Profile", Icons.Outlined.Person, "Profile")
}