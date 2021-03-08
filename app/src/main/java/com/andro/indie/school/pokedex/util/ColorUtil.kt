package com.andro.indie.school.pokedex.util

import androidx.compose.ui.graphics.Color
import com.andro.indie.school.pokedex.ui.theme.highStat
import com.andro.indie.school.pokedex.ui.theme.lowStat
import com.andro.indie.school.pokedex.ui.theme.mediumStat
import com.andro.indie.school.pokedex.ui.theme.veryLowStat
import kotlin.math.roundToInt

/**
 * Created by herisulistiyanto on 07/03/21.
 * KjokenKoddinger
 */

object ColorUtil {

    fun getStatColor(percentage: Float): Color {
        return when (percentage) {
            in 0.1..0.2 -> veryLowStat
            in 0.2..0.3 -> lowStat
            in 0.3..0.6 -> mediumStat
            in 0.6..1.0 -> highStat
            else -> Color.Red
        }
    }

    fun rgbToArgbColor(rgb: Int): Color {
        val red = (rgb shr 16) and 0xFF
        val green = (rgb shr 8) and 0xFF
        val blue = rgb and 0xFF

        return Color(red, green, blue)
    }

    fun rgbToArgb(rgb: Int): Int {
        val alpha = (0xFF shl 24)
        return (alpha or rgb)
    }

    fun darkenColor(color: Int, factor: Float = 0.6f): Int {
        val alpha = (color shr 24) and 0xFF
        val red = (color shr 16) and 0xFF
        val green = (color shr 8) and 0xFF
        val blue = color and 0xFF

        val darkenA = (alpha * factor).roundToInt()
        val darkenR = (red * factor).roundToInt()
        val darkenG = (green * factor).roundToInt()
        val darkenB = (blue * factor).roundToInt()

        return ((darkenA and 0xFF) shl 24) or
            ((darkenR and 0xFF) shl 16) or
            ((darkenG and 0xFF) shl 8) or
            (darkenB and 0xFF)
    }

}