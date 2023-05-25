package com.navi.musicplayerapp.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Brush
import androidx.navigation.compose.rememberNavController
import com.navi.musicplayerapp.ui.components.BottomNavigationComponent
import com.navi.musicplayerapp.ui.navigation.GraphDestinations
import com.navi.musicplayerapp.ui.uidefault.theme.PrimaryColor
import com.navi.musicplayerapp.ui.uidefault.theme.TertiaryColor
import com.navi.musicplayerapp.ui.uidefault.theme.dp24

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomMainScreen() {
    val navigationController = rememberNavController()
    Scaffold(bottomBar = { BottomNavigationComponent(navigationController) }) {
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
            GraphDestinations(navigationController)
        }
    }
}