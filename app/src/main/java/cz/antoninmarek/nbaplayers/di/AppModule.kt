package cz.antoninmarek.nbaplayers.di

import cz.antoninmarek.nbaplayers.data.repository.PlayerRepositoryImpl
import cz.antoninmarek.nbaplayers.data.repository.TeamRepositoryImpl
import cz.antoninmarek.nbaplayers.domain.repository.PlayerRepository
import cz.antoninmarek.nbaplayers.domain.repository.TeamRepository
import cz.antoninmarek.nbaplayers.domain.usecase.GetPlayerDetailUseCase
import cz.antoninmarek.nbaplayers.domain.usecase.GetPlayerImageUseCase
import cz.antoninmarek.nbaplayers.domain.usecase.GetPlayersUseCase
import cz.antoninmarek.nbaplayers.domain.usecase.GetTeamDetailUseCase
import org.koin.dsl.module

/**
 * Provides application-level dependencies.
 */
val appModule = module {
    /**
     * Provides a singleton instance of PlayerRepository.
     */
    single<PlayerRepository> { PlayerRepositoryImpl(get(), get()) }

    /**
     * Provides a singleton instance of TeamRepository.
     */
    single<TeamRepository> { TeamRepositoryImpl(get()) }

    /**
     * Provides a singleton instance of GetPlayersUseCase.
     */
    single { GetPlayersUseCase(get()) }

    /**
     * Provides a singleton instance of GetPlayerDetailUseCase.
     */
    single { GetPlayerDetailUseCase(get()) }

    /**
     * Provides a singleton instance of GetTeamDetailUseCase.
     */
    single { GetTeamDetailUseCase(get()) }

    /**
     * Provides a singleton instance of GetPlayerImageUseCase.
     */
    single { GetPlayerImageUseCase(get()) }
}