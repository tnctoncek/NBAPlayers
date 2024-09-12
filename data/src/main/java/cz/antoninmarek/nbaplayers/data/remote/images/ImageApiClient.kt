package cz.antoninmarek.nbaplayers.data.remote.images

/**
 * Client for accessing image-related API services.
 *
 * @property apiService The service used to fetch player image url.
 */
class ImageApiClient(
    private val apiService: ImageApiService
) {
    /**
     * Fetches the image of a player based on the provided query.
     *
     * @param query The search player name for the player's image url.
     * @return The URL of the player's image, or null if not found.
     */
    suspend fun getPlayerImage(query: String) = apiService.getPlayerImage(query)
}