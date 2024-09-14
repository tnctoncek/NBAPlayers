package cz.antoninmarek.nbaplayers.presentation.playerdetail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cz.antoninmarek.nbaplayers.domain.model.Player
import cz.antoninmarek.nbaplayers.presentation.R
import cz.antoninmarek.nbaplayers.presentation.ui.PlayerImage

/**
 * Displays the details of a player.
 *
 * @param viewModel The ViewModel that provides the player details.
 * @param onTeamClick Callback to handle team item click.
 * @param onNavigateUp Callback to handle the navigation up action.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlayerDetailScreen(
    viewModel: PlayerDetailViewModel,
    onTeamClick: (Int) -> Unit,
    onNavigateUp: () -> Unit
) {
    val playerState by viewModel.playerState.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { },
                navigationIcon = {
                    IconButton(onClick = onNavigateUp) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back", tint = MaterialTheme.colorScheme.onSurface)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.surface)
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(innerPadding)
        ) {
            when {
                playerState.isLoading -> CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                playerState.error != null -> Text("Error: ${playerState.error}", modifier = Modifier.align(Alignment.Center), style = MaterialTheme.typography.bodyLarge, color = MaterialTheme.colorScheme.error)
                playerState.player != null -> PlayerDetailContent(playerState.player!!, playerState.imageUrl, onTeamClick)
            }
        }
    }
}

/**
 * Displays the content of the player detail screen.
 *
 * @param player The player to display.
 * @param imageUrl The URL of the player's image.
 * @param onTeamClick Callback to handle team item click.
 */
@Composable
fun PlayerDetailContent(player: Player, imageUrl: String?, onTeamClick: (Int) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        PlayerHeader(player, imageUrl)
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Spacer(modifier = Modifier.height(24.dp))
            PlayerStats(player)
            Spacer(modifier = Modifier.height(24.dp))
            PlayerBiography(player)
            Spacer(modifier = Modifier.height(24.dp))
            PlayerAdditionalInfo(player)
            Spacer(modifier = Modifier.height(24.dp))
            TeamButton(player, onTeamClick)
        }
    }
}

/**
 * Displays the header section of the player detail screen.
 *
 * @param player The player to display.
 * @param imageUrl The URL of the player's image.
 */
@Composable
fun PlayerHeader(player: Player, imageUrl: String?) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(240.dp)
    ) {

        Column(
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(16.dp)
        ) {
            Text(
                text = player.jerseyNumber.toString(),
                fontSize = 88.sp,
                fontWeight = FontWeight.Thin,
                color = Color.DarkGray.copy(alpha = 0.5f),
            )
        }

        PlayerImage(
            model = imageUrl ?: R.mipmap.default_player_image,
            contentDescription = "Player Image",
            modifier = Modifier.fillMaxHeight(),
            contentScale = ContentScale.FillHeight,
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(MaterialTheme.colorScheme.background.copy(alpha = 0f), MaterialTheme.colorScheme.background),
                        startY = 0f,
                        endY = 900f
                    )
                )
        )

        Column(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(16.dp)
        ) {
            Text(
                text = player.firstName,
                style = MaterialTheme.typography.headlineSmall,
                color = Color.Gray
            )
            Text(
                text = player.lastName,
                style = MaterialTheme.typography.displayLarge,
                color = MaterialTheme.colorScheme.onBackground
            )
        }
    }
}

/**
 * Displays the player's statistics.
 *
 * @param player The player whose statistics are to be displayed.
 */
@Composable
fun PlayerStats(player: Player) {
    Row(
        modifier = Modifier.fillMaxWidth()
    ) {
        StatItem("Height", player.height, Modifier.weight(1f))
        Spacer(modifier = Modifier.width(16.dp))
        StatItem("Weight", "${player.weight} lbs", Modifier.weight(1f))
        Spacer(modifier = Modifier.width(16.dp))
        StatItem("Position", player.position, Modifier.weight(1f))
    }
}

/**
 * Displays a single statistic item.
 *
 * @param label The label of the statistic.
 * @param value The value of the statistic.
 * @param modifier The modifier to be applied to the item.
 */
@Composable
fun StatItem(label: String, value: String, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(8.dp))
            .background(MaterialTheme.colorScheme.surface)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(value, style = MaterialTheme.typography.titleMedium, color = Color.White, textAlign = TextAlign.Center)
        Spacer(modifier = Modifier.height(8.dp))
        Text(label, style = MaterialTheme.typography.bodyMedium, color = Color.Gray, textAlign = TextAlign.Center)
    }
}

/**
 * Displays the player's biography.
 *
 * @param player The player whose biography is to be displayed.
 */
@Composable
fun PlayerBiography(player: Player) {
    Column {
        SectionHeader("Biography")
        Text(
            "Player from ${player.country}, drafted in ${player.draftYear}. Plays for ${player.team.fullName} in the ${player.team.conference} conference.",
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Gray
        )
    }
}

/**
 * Displays additional information about the player.
 *
 * @param player The player whose additional information is to be displayed.
 */
@Composable
fun PlayerAdditionalInfo(player: Player) {
    Column {
        SectionHeader("Additional Information")
        InfoItem("Jersey Number", player.jerseyNumber.toString())
        InfoItem("College", player.college)
        InfoItem("Draft Round", player.draftRound.toString())
        InfoItem("Draft Number", player.draftNumber.toString())
    }
}

/**
 * Displays a label and its corresponding value in a row.
 *
 * @param label The label of the information item.
 * @param value The value of the information item.
 */
@Composable
fun InfoItem(label: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(label, style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f))
        Text(value, style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onBackground)
    }
}

/**
 * Displays a section header with the given title.
 *
 * @param title The title of the section.
 */
@Composable
fun SectionHeader(title: String) {
    Text(
        text = title,
        style = MaterialTheme.typography.titleMedium,
        color = MaterialTheme.colorScheme.onBackground,
        modifier = Modifier.padding(bottom = 8.dp)
    )
}

/**
 * Displays a button to view the player's team details.
 *
 * @param player The player whose team details are to be viewed.
 * @param onTeamClick Callback to handle team item click.
 */
@Composable
fun TeamButton(player: Player, onTeamClick: (Int) -> Unit) {
    Button(
        onClick = { onTeamClick(player.team.id) },
        modifier = Modifier.fillMaxWidth()
    ) {
        Text("View ${player.team.fullName} Details", style = MaterialTheme.typography.labelLarge)
    }
}