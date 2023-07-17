package com.mego.mycrypto.compose.bottmNavigation

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.mego.mycrypto.navigation.Screen

@Composable
fun MyBottomNavigation(navController: NavController) {
    val bottomNavItems = listOf(Screen.Home, Screen.Rewards, Screen.Profile)

    val navBackStack by navController.currentBackStackEntryAsState()

    BottomNavigation (backgroundColor = Color.White){
        bottomNavItems.forEach{ screen ->
            val isSelected = navBackStack?.destination?.route == screen.route
            BottomNavigationItem(
                selected = isSelected,
                onClick = { navController.navigate(screen.route) {
                    popUpTo(navController.graph.startDestinationId) {
                        saveState = true
                    }
                    launchSingleTop = true
                    restoreState = true
                } },
                icon = { Icon(imageVector = screen.icon, contentDescription = "", tint = Color.Gray) })

        }
    }
}