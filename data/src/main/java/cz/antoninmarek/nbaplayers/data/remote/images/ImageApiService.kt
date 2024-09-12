package cz.antoninmarek.nbaplayers.data.remote.images

/**
 * Service interface for fetching player images from a remote source.
 */
interface ImageApiService {
    /**
     * Fetches the image of a player based on the provided query.
     *
     * @param query The search player name for the player's image url.
     * @return The URL of the player's image, or null if not found.
     */
    suspend fun getPlayerImage(query: String): String?
}