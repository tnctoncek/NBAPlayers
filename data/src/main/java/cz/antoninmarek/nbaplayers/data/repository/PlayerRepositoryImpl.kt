package cz.antoninmarek.nbaplayers.data.repository

import android.util.Log
import cz.antoninmarek.nbaplayers.data.model.toDomainModel
import cz.antoninmarek.nbaplayers.data.remote.images.ImageApiClient
import cz.antoninmarek.nbaplayers.data.remote.nba.NBAApiClient
import cz.antoninmarek.nbaplayers.domain.model.Player
import cz.antoninmarek.nbaplayers.domain.repository.PlayerRepository

/**
 * Implementation of the PlayerRepository interface.
 *
 * @property nbaApiClient The client used to fetch NBA data.
 * @property imagesApiClient The client used to fetch player images.
 */
class PlayerRepositoryImpl(
    private val nbaApiClient: NBAApiClient,
    private val imagesApiClient: ImageApiClient
) : PlayerRepository {

    /**
     * Fetches a list of players based on the provided pagination parameters.
     *
     * @param page The page number to fetch.
     * @param perPage The number of players per page.
     * @return A list of players.
     */
    override suspend fun getPlayers(page: Int, perPage: Int): List<Player> {
        val players = nbaApiClient.getPlayers(page, perPage).data.map { it.toDomainModel() }
        Log.d("PlayerRepositoryImpl", "getPlayers: $players")
        return players
    }

    /**
     * Fetches the details of a player based on the provided player ID.
     *
     * @param id The ID of the player to fetch details for.
     * @return The details of the player.
     */
    override suspend fun getPlayerDetail(id: Int): Player {
        return nbaApiClient.getPlayerDetail(id).data.toDomainModel()
    }

    /**
     * Fetches the image URL of a player based on the provided query.
     *
     * @param query The search query for the player's image URL.
     * @return The URL of the player's image, or null if not found.
     */
    override suspend fun getPlayerImage(query: String): String? {
        return imagesApiClient.getPlayerImage(query)
    }

}