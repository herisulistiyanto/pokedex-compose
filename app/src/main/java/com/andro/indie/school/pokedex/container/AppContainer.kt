package com.andro.indie.school.pokedex.container

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import com.andro.indie.school.pokedex.ui.theme.PokedexTheme

/**
 * Created by herisulistiyanto on 02/03/21.
 * KjokenKoddinger
 */

@Composable
fun AppContainer(content: @Composable () -> Unit) {
    PokedexTheme {
        Surface(color = MaterialTheme.colors.background) {
            content()
        }
    }
}