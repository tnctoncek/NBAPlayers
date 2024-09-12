package cz.antoninmarek.nbaplayers.domain.repository

import cz.antoninmarek.nbaplayers.domain.model.Player

/**
 * Repository interface for fetching player data.
 */
interface PlayerRepository {

    /**
     * Fetches a list of players based on the provided pagination parameters.
     *
     * @param page The page number to fetch.
     * @param perPage The number of players per page.
     * @return A list of players.
     */
    suspend fun getPlayers(page: Int, perPage: Int): List<Player>

    /**
     * Fetches the details of a player based on the provided player ID.
     *
     * @param id The ID of the player to fetch details for.
     * @return The details of the player.
     */
    suspend fun getPlayerDetail(id: Int): Player

    /**
     * Fetches the image URL of a player based on the provided query.
     *
     * @param query The search query for the player's image URL.
     * @return The URL of the player's image, or null if not found.
     */
    suspend fun getPlayerImage(query: String): String?
}