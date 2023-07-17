package com.mego.mycrypto.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.mego.mycrypto.compose.bottmNavigation.MyBottomNavigation
import com.mego.mycrypto.navigation.MyNavGraph
import com.mego.mycrypto.navigation.Screen
import com.mego.mycrypto.ui.theme.MyCryptoTheme
import org.koin.androidx.compose.koinViewModel


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyCryptoTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MyApp()
                }
            }
        }
    }
}

@Composable
fun MyApp(modifier: Modifier=Modifier ) {

    val navController = rememberNavController()

    Scaffold( bottomBar = { MyBottomNavigation(navController = navController)}) {
        innerPadding->
        Box(modifier = Modifier.padding(innerPadding)){
            MyNavGraph(navHostController = navController)
        }
    }
}




@Preview
@Composable
fun MyAppPreview() {
    MyCryptoTheme() {
        MyApp()
    }
}