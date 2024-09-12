package cz.antoninmarek.nbaplayers.presentation.playerdetail

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cz.antoninmarek.nbaplayers.domain.model.Player
import cz.antoninmarek.nbaplayers.presentation.R
import cz.antoninmarek.nbaplayers.presentation.ui.PlayerImage

/**
 * Composable function for displaying the player detail screen.
 *
 * @param viewModel The ViewModel that provides the player state.
 * @param onTeamClick Callback function when a team is clicked.
 * @param onNavigateUp Callback function when the navigation up action is triggered.
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
                title = { Text("Player Details") },
                navigationIcon = {
                    IconButton(onClick = onNavigateUp) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { innerPadding ->
        Box(modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)) {
            when {
                playerState.isLoading -> CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                playerState.error != null -> Text("Error: ${playerState.error}", modifier = Modifier.align(Alignment.Center))
                playerState.player != null -> PlayerDetailContent(
                    player = playerState.player!!,
                    imageUrl = playerState.imageUrl,
                    onTeamClick = onTeamClick
                )
            }
        }
    }
}

/**
 * Composable function for displaying the content of the player detail screen.
 *
 * @param player The player whose details are to be displayed.
 * @param imageUrl The URL of the player's image.
 * @param onTeamClick Callback function when a team is clicked.
 */
@Composable
fun PlayerDetailContent(player: Player, imageUrl: String?, onTeamClick: (Int) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        PlayerImage(
            model = imageUrl ?: R.mipmap.default_player_image,
            contentDescription = "Player Image"
        )

        Spacer(modifier = Modifier.height(16.dp))
        Text("${player.firstName} ${player.lastName}", style = MaterialTheme.typography.headlineMedium)

        Spacer(modifier = Modifier.height(8.dp))
        Text("Position: ${player.position}")
        Text("Height: ${player.height}")
        Text("Weight: ${player.weight} lbs")
        Text("Jersey Number: ${player.jerseyNumber}")
        Text("College: ${player.college}")
        Text("Country: ${player.country}")

        Spacer(modifier = Modifier.height(16.dp))
        Text("Draft Information", style = MaterialTheme.typography.titleMedium)

        Spacer(modifier = Modifier.height(8.dp))
        Text("Draft Year: ${player.draftYear}")
        Text("Draft Round: ${player.draftRound}")
        Text("Draft Number: ${player.draftNumber}")

        Spacer(modifier = Modifier.height(16.dp))
        Text("Team Information", style = MaterialTheme.typography.titleMedium)

        Spacer(modifier = Modifier.height(8.dp))
        player.team.let { team ->
            Text("Team: ${team.fullName}")
            Text("Conference: ${team.conference}")
            Text("Division: ${team.division}")

            Button(
                onClick = { onTeamClick(team.id) },
                modifier = Modifier.align(Alignment.CenterHorizontally).padding(10.dp)
            ) {
                Text("Team Details")
            }
        }
    }
}