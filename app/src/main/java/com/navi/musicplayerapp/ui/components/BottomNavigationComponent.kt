package com.navi.musicplayerapp.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.navi.musicplayerapp.ui.navigation.ScreenRoutes
import com.navi.musicplayerapp.ui.uidefault.theme.*

@Composable
fun BottomNavigationComponent(navigationController: NavHostController) {

    val navBackStackEntry by navigationController.currentBackStackEntryAsState()
    val route = navBackStackEntry?.destination?.route

    Box(
        Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(PrimaryColor, TertiaryColor)
                )
            )
            .alpha(0.8f)
            .padding(dp24)
    ) {
        NavigationBar(
            Modifier
                .clip(RoundedCornerShape(dp16))
                .alpha(0.5f)
                .background(SecondaryColor)
                .align(Alignment.BottomCenter)
        ) {
            val listNavigationItem =
                listOf(ScreenRoutes.Home, ScreenRoutes.Playing, ScreenRoutes.Favorite)
            listNavigationItem.forEach { currentScreen ->
                NavigationBarItem(
                    selected = route == currentScreen.route,
                    icon = {
                    Icon(
                        currentScreen.icon,
                        contentDescription = currentScreen.route,
                        Modifier.size(dp32)
                    )
                },
                    onClick = {
                        navigationController.navigate(currentScreen.route)
                    }
                )
            }
        }
    }
}
