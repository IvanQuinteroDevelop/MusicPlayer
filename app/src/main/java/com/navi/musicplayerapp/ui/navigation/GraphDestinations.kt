package com.navi.musicplayerapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.composable
import com.navi.musicplayerapp.ui.screens.FavoriteScreen
import com.navi.musicplayerapp.ui.screens.HomeScreen
import com.navi.musicplayerapp.ui.screens.PlayingScreen

@Composable
fun GraphDestinations(navigationController: NavHostController) {

    NavHost(navController = navigationController, startDestination = ScreenRoutes.Home.route) {

        composable(ScreenRoutes.Home.route) {
            HomeScreen(viewModel = hiltViewModel())
        }

        composable(ScreenRoutes.Playing.route) {
            PlayingScreen(viewModel = hiltViewModel())
        }

        composable(ScreenRoutes.Favorite.route) {
            FavoriteScreen(viewModel = hiltViewModel(), navigationController)
        }
    }
}