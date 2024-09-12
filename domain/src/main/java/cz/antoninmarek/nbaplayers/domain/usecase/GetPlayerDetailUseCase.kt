package cz.antoninmarek.nbaplayers.domain.usecase

import cz.antoninmarek.nbaplayers.domain.model.Player
import cz.antoninmarek.nbaplayers.domain.repository.PlayerRepository

/**
 * Use case for fetching the details of a player.
 *
 * @property playerRepository The repository used to fetch player data.
 */
class GetPlayerDetailUseCase(private val playerRepository: PlayerRepository) {

    /**
     * Invokes the use case to fetch the details of a player based on the provided player ID.
     *
     * @param id The ID of the player to fetch details for.
     * @return The details of the player.
     */
    suspend operator fun invoke(id: Int): Player {
        return playerRepository.getPlayerDetail(id)
    }
}