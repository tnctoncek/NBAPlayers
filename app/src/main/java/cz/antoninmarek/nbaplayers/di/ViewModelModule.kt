package cz.antoninmarek.nbaplayers.di

import cz.antoninmarek.nbaplayers.presentation.playerdetail.PlayerDetailViewModel
import cz.antoninmarek.nbaplayers.presentation.playerlist.PlayerListViewModel
import cz.antoninmarek.nbaplayers.presentation.teamdetail.TeamDetailViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * Provides ViewModel dependencies for the application.
 */
val viewModelModule = module {
    /**
     * Provides a singleton instance of PlayerListViewModel.
     */
    viewModel { PlayerListViewModel(get()) }

    /**
     * Provides a singleton instance of PlayerDetailViewModel with playerId as a parameter.
     */
    viewModel { (playerId: Int) -> PlayerDetailViewModel(playerId, get(), get()) }

    /**
     * Provides a singleton instance of TeamDetailViewModel with teamId as a parameter.
     */
    viewModel { (teamId: Int) -> TeamDetailViewModel(teamId, get()) }
}