package com.navi.musicplayerapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.navi.musicplayerapp.ui.screens.BottomMainScreen

object MainDestination {
    const val route = "main_screen"
}

@Composable
fun GraphStart(
    navController: NavHostController = rememberNavController()
) {
    NavHost(navController = navController, startDestination = MainDestination.route) {
        composable(MainDestination.route) {
            BottomMainScreen()
        }
    }
}