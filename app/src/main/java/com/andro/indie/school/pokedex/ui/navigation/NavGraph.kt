package com.andro.indie.school.pokedex.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.HiltViewModelFactory
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.navigate
import androidx.navigation.compose.rememberNavController
import com.andro.indie.school.pokedex.presentation.detail.DetailViewModel
import com.andro.indie.school.pokedex.presentation.detail.component.DetailScreen
import com.andro.indie.school.pokedex.presentation.home.HomeViewModel
import com.andro.indie.school.pokedex.presentation.home.component.HomeScreen

/**
 * Created by herisulistiyanto on 06/03/21.
 * KjokenKoddinger
 */

@Composable
fun NavGraph(
    startDestination: String = ScreenRoute.HomeScreen.route
) {

    val navController = rememberNavController()
    val action = remember(navController) { MainAction(navController) }

    // Register Screen
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {

        // Home
        composable(ScreenRoute.HomeScreen.route) { navBackStackEntry ->
            val viewModelFactory = HiltViewModelFactory(LocalContext.current, navBackStackEntry)
            val viewModel = viewModel<HomeViewModel>(
                key = ScreenRoute.HomeScreen.route,
                factory = viewModelFactory
            )
            HomeScreen(
                viewModel = viewModel,
                onNavigateDetail = navController::navigate
            )
        }

        // Detail
        composable(
            route = ScreenRoute.DetailScreen.route + "/{pokemonId}",
            arguments = listOf(
                navArgument("pokemonId") {
                    type = NavType.StringType
                }
            )
        ) { navBackStackEntry ->
            val viewModelFactory = HiltViewModelFactory(LocalContext.current, navBackStackEntry)
            val viewModel = viewModel<DetailViewModel>(
                key = ScreenRoute.DetailScreen.route,
                factory = viewModelFactory
            )
            DetailScreen(
                viewModel = viewModel,
                pokemonId = navBackStackEntry.arguments?.getString("pokemonId").orEmpty()
            )
        }
    }

}

class MainAction(navController: NavController) {
    val upPress: () -> Unit = {
        navController.navigateUp()
    }
}