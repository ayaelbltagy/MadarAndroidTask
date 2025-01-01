package com.example.madarandroid.presentation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController


@Composable
fun NavigationStack() {
    val navController = rememberNavController()
    val viewModel = hiltViewModel<UserViewModel>()
    NavHost(navController = navController, startDestination = Screen.Main.route) {
        composable(route = Screen.Main.route) {
            HomeScreen(viewModel,navController = navController)
        }
        composable(route = Screen.Detail.route

        ) {
            DetailsScreen(viewModel)
        }
    }
}