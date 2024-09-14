package cz.antoninmarek.nbaplayers.presentation.theme

import androidx.compose.ui.graphics.Color

// Základní barvy
val Blue = Color(0xFF1E88E5)
val LightBlue = Color(0xFF64B5F6)
val DarkBlue = Color(0xFF0D47A1)
val White = Color.White
val Gray = Color(0xFF9E9E9E)
val LightGray = Color(0xFFE0E0E0)

// Barvy pozadí
val DarkBackground = Color(0xFF121212)
val DarkSurface = Color(0xFF1E1E1E)

// Barvy textu
val PrimaryText = White
val SecondaryText = LightGray
val DisabledText = Gray.copy(alpha = 0.6f)

// Barvy pro specifické účely
val PlayerPositionBackground = Blue
val PlayerPositionText = White
val CardBackground = DarkSurface
val ButtonBackground = Blue
val ButtonText = White