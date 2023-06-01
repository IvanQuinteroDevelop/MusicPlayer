package com.navi.musicplayerapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.navi.musicplayerapp.ui.screens.FavoriteScreen
import com.navi.musicplayerapp.ui.screens.HomeScreen
import com.navi.musicplayerapp.ui.screens.PlayingScreen
import com.navi.musicplayerapp.ui.viewmodel.MusicViewModel
import com.navi.musicplayerapp.ui.viewmodel.PlayerViewModel

@Composable
fun GraphDestinations(navigationController: NavHostController) {
    val playerViewModel: PlayerViewModel = hiltViewModel()
    val musicViewModel: MusicViewModel = hiltViewModel()

    NavHost(navController = navigationController, startDestination = ScreenRoutes.Home.route) {

        composable(ScreenRoutes.Home.route) {
            HomeScreen(
                musicViewModel = musicViewModel,
                playerViewModel = playerViewModel,
                navigationController = navigationController
            )
        }

        composable(ScreenRoutes.Playing.route) {
            PlayingScreen(playerViewModel = playerViewModel)
        }

        composable(ScreenRoutes.Favorite.route) {
            FavoriteScreen(
                musicViewModel = musicViewModel,
                playerViewModel = playerViewModel,
                navigationController = navigationController
            )
        }
    }
}