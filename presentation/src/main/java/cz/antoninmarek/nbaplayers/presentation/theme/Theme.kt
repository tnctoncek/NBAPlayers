package cz.antoninmarek.nbaplayers.presentation.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable

private val DarkColorScheme = darkColorScheme(
    primary = Blue,
    secondary = LightBlue,
    tertiary = DarkBlue,
    background = DarkBackground,
    surface = DarkSurface,
    onPrimary = PrimaryText,
    onSecondary = SecondaryText,
    onTertiary = PrimaryText,
    onBackground = PrimaryText,
    onSurface = PrimaryText,
)

@Composable
fun NBAPlayersTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = DarkColorScheme,
        typography = Typography,
        content = content
    )
}

// Rozšíření pro snadnější přístup k vlastním barvám
object NBATheme {
    val playerPositionBackground get() = PlayerPositionBackground
    val playerPositionText get() = PlayerPositionText
    val cardBackground get() = CardBackground
    val buttonBackground get() = ButtonBackground
    val buttonText get() = ButtonText
    val disabledText get() = DisabledText
}