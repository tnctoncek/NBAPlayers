package cz.antoninmarek.nbaplayers.domain.usecase

import cz.antoninmarek.nbaplayers.domain.repository.PlayerRepository

/**
 * Use case for fetching the image URL of a player.
 *
 * @property playerRepository The repository used to fetch player data.
 */
class GetPlayerImageUseCase(private val playerRepository: PlayerRepository) {

    /**
     * Invokes the use case to fetch the image URL of a player based on the provided query.
     *
     * @param query The search query for the player's image URL.
     * @return The URL of the player's image, or null if not found.
     */
    suspend operator fun invoke(query: String): String? {
        return playerRepository.getPlayerImage(query)
    }
}