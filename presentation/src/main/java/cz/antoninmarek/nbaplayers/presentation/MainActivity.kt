package cz.antoninmarek.nbaplayers.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import cz.antoninmarek.nbaplayers.presentation.playerdetail.PlayerDetailScreen
import cz.antoninmarek.nbaplayers.presentation.playerdetail.PlayerDetailViewModel
import cz.antoninmarek.nbaplayers.presentation.playerlist.PlayerListScreen
import cz.antoninmarek.nbaplayers.presentation.playerlist.PlayerListViewModel
import cz.antoninmarek.nbaplayers.presentation.teamdetail.TeamDetailScreen
import cz.antoninmarek.nbaplayers.presentation.teamdetail.TeamDetailViewModel
import cz.antoninmarek.nbaplayers.presentation.theme.NBAPlayersTheme
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

/**
 * Main activity for the NBA Players application.
 */
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NBAPlayersTheme {
                NBAApp()
            }
        }
    }
}

/**
 * Composable function for setting up the NBA Players application navigation.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NBAApp() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination?.route ?: "playerList"

    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "playerList",
            modifier = Modifier.padding(innerPadding)
        ) {

            composable(
                "playerList"
            ) {
                val viewModel = koinViewModel<PlayerListViewModel>()
                PlayerListScreen(
                    viewModel = viewModel,
                    onPlayerClick = { playerId ->
                        navController.navigate("playerDetail/$playerId")
                    },
                    currentDestination = currentDestination,
                    onNavigateUp = { navController.navigateUp() }
                )
            }

            composable(
                "playerDetail/{playerId}",
                arguments = listOf(navArgument("playerId") { type = NavType.IntType })
            ) { backStackEntry ->
                val playerId = backStackEntry.arguments?.getInt("playerId") ?: return@composable
                val viewModel = koinViewModel<PlayerDetailViewModel> { parametersOf(playerId) }
                PlayerDetailScreen(
                    viewModel = viewModel,
                    onTeamClick = { teamId ->
                        navController.navigate("teamDetail/$teamId")
                    },
                    onNavigateUp = { navController.navigateUp() }
                )
            }

            composable(
                "teamDetail/{teamId}",
                arguments = listOf(navArgument("teamId") { type = NavType.IntType })
            ) { backStackEntry ->
                val teamId = backStackEntry.arguments?.getInt("teamId") ?: return@composable
                val viewModel = koinViewModel<TeamDetailViewModel> { parametersOf(teamId) }
                TeamDetailScreen(
                    viewModel = viewModel,
                    currentDestination = currentDestination,
                    onNavigateUp = { navController.navigateUp() }
                )
            }

        }
    }
}