package com.navi.musicplayerapp.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.ui.graphics.vector.ImageVector

sealed class ScreenRoutes(val route: String, val icon: ImageVector) {
    object Home: ScreenRoutes("home_screen", Icons.Filled.Home)
    object Playing: ScreenRoutes("play_screen", Icons.Filled.PlayArrow)
    object Favorite: ScreenRoutes("favorite_screen", Icons.Filled.Favorite)
}
