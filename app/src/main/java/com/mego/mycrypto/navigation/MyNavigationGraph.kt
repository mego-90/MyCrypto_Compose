package com.mego.mycrypto.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.mego.mycrypto.compose.home.HomeScreen
import com.mego.mycrypto.compose.rewards.RewardsScreen
import com.mego.mycrypto.compose.setting.SettingScreen
import com.mego.mycrypto.domain.Coin

@Composable
fun MyNavGraph(navHostController: NavHostController) {
    NavHost(navController = navHostController, startDestination = Screen.Home.route ) {
        composable(Screen.Home.route) {
            HomeScreen()
        }
        composable(Screen.Rewards.route) {
            RewardsScreen()
        }
        composable(Screen.Profile.route) {
            SettingScreen()
        }
    }
}