package com.andro.indie.school.pokedex.ui.navigation

/**
 * Created by herisulistiyanto on 06/03/21.
 * KjokenKoddinger
 */
 
sealed class ScreenRoute(val route: String) {

    object HomeScreen : ScreenRoute("homeScreen")
    object DetailScreen : ScreenRoute("detailScreen")

}