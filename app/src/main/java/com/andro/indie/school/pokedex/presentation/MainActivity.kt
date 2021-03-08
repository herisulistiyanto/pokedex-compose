package com.andro.indie.school.pokedex.presentation

import android.content.Context
import android.os.Bundle
import android.view.WindowManager
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import com.andro.indie.school.pokedex.R
import com.andro.indie.school.pokedex.container.AppContainer
import com.andro.indie.school.pokedex.presentation.home.component.HomeScreen
import com.andro.indie.school.pokedex.ui.navigation.NavGraph
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by herisulistiyanto on 02/03/21.
 * KjokenKoddinger
 */

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            changeStatusBarColor(LocalContext.current)
            AppContainer {
                NavGraph()
            }
        }
    }

    @Suppress("DEPRECATION")
    private fun changeStatusBarColor(context: Context) {
        window?.run {
            clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            statusBarColor = ContextCompat.getColor(context, R.color.dark_grey)
        }
    }

}