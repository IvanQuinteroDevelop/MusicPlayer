package com.navi.musicplayerapp.ui.screens

import android.annotation.SuppressLint
import android.app.Activity
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.navi.musicplayerapp.ui.components.BottomNavigationComponent
import com.navi.musicplayerapp.ui.navigation.GraphDestinations

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomMainScreen(activity: Activity) {
    val navigationController = rememberNavController()
    Scaffold(bottomBar = { BottomNavigationComponent(navigationController) }) {
        GraphDestinations(navigationController, activity)
    }
}