package theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Typography
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val LightColors = lightColors(
    primary = buttonColor,
    onPrimary = mainTextColor,
    background = mainBackgroundColor,
)

private val DarkColors = darkColors(
    primary = buttonColor,
    onPrimary = mainTextColor,
    background = mainBackgroundColor,
)

@Composable
fun MoneyManagerTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        darkTheme -> DarkColors
        else -> LightColors
    }
    MaterialTheme(
        colors = colorScheme,
        typography = Typography,
        content = content
    )
}