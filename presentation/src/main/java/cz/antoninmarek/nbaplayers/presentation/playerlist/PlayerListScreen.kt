package cz.antoninmarek.nbaplayers.presentation.playerlist

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cz.antoninmarek.nbaplayers.domain.model.Player
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter

/**
 * Composable function for displaying the player list screen.
 *
 * @param viewModel The ViewModel that provides the player list state.
 * @param onPlayerClick Callback function when a player is clicked.
 * @param currentDestination The current navigation destination.
 * @param onNavigateUp Callback function when the navigation up action is triggered.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlayerListScreen(
    viewModel: PlayerListViewModel,
    onPlayerClick: (Int) -> Unit,
    currentDestination: String,
    onNavigateUp: () -> Unit
) {
    val players by viewModel.players.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val listState = rememberLazyListState()

    LaunchedEffect(listState) {
        snapshotFlow {
            val layoutInfo = listState.layoutInfo
            val totalItemsNumber = layoutInfo.totalItemsCount
            val lastVisibleItemIndex = (layoutInfo.visibleItemsInfo.lastOrNull()?.index ?: 0) + 1

            lastVisibleItemIndex > (totalItemsNumber - 2)
        }
            .distinctUntilChanged()
            .filter { it }
            .collect {
                viewModel.loadPlayers()
            }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("NBA Players") }
            )
        }
    ) { innerPadding ->
        Box(modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)) {
            LazyColumn(
                state = listState,
                modifier = Modifier.fillMaxSize()
            ) {
                items(players) { player ->
                    PlayerItem(player = player, onClick = { onPlayerClick(player.id) })
                }
                item {
                    if (isLoading) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator()
                        }
                    }
                }
            }

            if (players.isEmpty() && !isLoading) {
                Text(
                    text = "No players found",
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }
    }
}

/**
 * Composable function for displaying a player item in the list.
 *
 * @param player The player to be displayed.
 * @param onClick Callback function when the player item is clicked.
 */
@Composable
fun PlayerItem(player: Player, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable(onClick = onClick)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "${player.firstName} ${player.lastName}", style = MaterialTheme.typography.titleMedium)
            Text(text = "Position: ${player.position}")
            Text(text = "Team: ${player.team?.fullName ?: "N/A"}")
        }
    }
}