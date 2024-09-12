package cz.antoninmarek.nbaplayers.presentation.teamdetail

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

/**
 * Composable function for displaying the team detail screen.
 *
 * @param viewModel The ViewModel that provides the team detail state.
 * @param currentDestination The current navigation destination.
 * @param onNavigateUp Callback function when the navigation up action is triggered.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TeamDetailScreen(
    viewModel: TeamDetailViewModel,
    currentDestination: String,
    onNavigateUp: () -> Unit
) {
    val teamDetail by viewModel.teamDetail.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Team Details") },
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
            teamDetail?.let { team ->
                Column(
                    modifier = Modifier
                        .verticalScroll(rememberScrollState())
                        .padding(16.dp)
                ) {
                    Text(text = "${team.fullName}", style = MaterialTheme.typography.headlineMedium)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(text = "City: ${team.city}")
                    Text(text = "Name: ${team.name}")
                    Text(text = "Abbreviation: ${team.abbreviation}")

                    Spacer(modifier = Modifier.height(16.dp))
                    Text(text = "League Information", style = MaterialTheme.typography.titleMedium)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(text = "Conference: ${team.conference}")
                    Text(text = "Division: ${team.division}")
                }
            } ?: run {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
        }
    }
}