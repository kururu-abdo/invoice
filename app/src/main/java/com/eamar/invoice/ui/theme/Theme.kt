package com.eamar.invoice.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import com.eamar.invoice.src.utils.Constants.mainColor
import com.eamar.invoice.src.utils.Constants.secondaryColor
import com.eamar.invoice.src.utils.Constants.thirdColor

private val DarkColorPalette = darkColors(
    primary = mainColor,
    primaryVariant = thirdColor,
    secondary = secondaryColor
)

private val LightColorPalette = lightColors(
    primary = mainColor,
    primaryVariant = thirdColor,
    secondary = secondaryColor ,


    /* Other default colors to override
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
)

@Composable
fun InvoiceTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}