package cz.antoninmarek.nbaplayers.presentation.playerlist

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import cz.antoninmarek.nbaplayers.domain.model.Player

import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter

/**
 * Displays a list of NBA players.
 *
 * @param viewModel The ViewModel that provides the list of players.
 * @param onPlayerClick Callback to handle player item click.
 * @param currentDestination The current navigation destination.
 * @param onNavigateUp Callback to handle the navigation up action.
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
            .collect { viewModel.loadPlayers() }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("NBA Players", color = Color.White) },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFF1E1E1E))
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFF121212))
                .padding(innerPadding)
        ) {
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
                            CircularProgressIndicator(color = Color.White)
                        }
                    }
                }
            }
            if (players.isEmpty() && !isLoading) {
                Text(
                    text = "No players found",
                    modifier = Modifier.align(Alignment.Center),
                    color = Color.White
                )
            }
        }
    }
}

/**
 * Displays a player item in the list.
 *
 * @param player The player to display.
 * @param onClick Callback to handle item click.
 */
@Composable
fun PlayerItem(player: Player, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .clip(RoundedCornerShape(8.dp))
            .clickable(onClick = onClick),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF1E1E1E))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(50.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color(0xFF1E88E5)),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = player.position,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            }
            Column(
                modifier = Modifier
                    .padding(start = 16.dp)
                    .weight(1f)
            ) {
                Text(
                    text = "${player.firstName} ${player.lastName}",
                    color = Color.White,
                    style = MaterialTheme.typography.titleMedium,
                )
                Text(
                    text = player.team?.fullName ?: "N/A",
                    color = Color.Gray,
                    style = MaterialTheme.typography.titleSmall,
                )
            }
        }
    }
}