package cz.antoninmarek.nbaplayers.domain.usecase

import android.util.Log
import cz.antoninmarek.nbaplayers.domain.model.Player
import cz.antoninmarek.nbaplayers.domain.repository.PlayerRepository

/**
 * Use case for fetching a list of players.
 *
 * @property playerRepository The repository used to fetch player data.
 */
class GetPlayersUseCase(private val playerRepository: PlayerRepository) {

    /**
     * Invokes the use case to fetch a list of players based on the provided pagination parameters.
     *
     * @param page The page number to fetch.
     * @param perPage The number of players per page. Default is 35.
     * @return A list of players.
     */
    suspend operator fun invoke(page: Int, perPage: Int = 35): List<Player> {
        Log.d("GetPlayersUseCase", "invoke page = $page, perPage = $perPage")
        return playerRepository.getPlayers(page, perPage)
    }
}