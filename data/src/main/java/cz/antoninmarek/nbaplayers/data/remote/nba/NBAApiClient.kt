package cz.antoninmarek.nbaplayers.data.remote.nba

/**
 * Client for accessing NBA-related API services.
 *
 * @property apiService The service used to fetch NBA data.
 */
class NBAApiClient(private val apiService: ApiService) {

    /**
     * Fetches a list of players based on the provided pagination parameters.
     *
     * @param page The page number to fetch.
     * @param perPage The number of players per page.
     * @return The result of the players fetch operation.
     */
    suspend fun getPlayers(page: Int, perPage: Int) = apiService.getPlayers(page, perPage)

    /**
     * Fetches the details of a player based on the provided player ID.
     *
     * @param id The ID of the player to fetch details for.
     * @return The result of the player detail fetch operation.
     */
    suspend fun getPlayerDetail(id: Int) = apiService.getPlayerDetail(id)

    /**
     * Fetches the details of a team based on the provided team ID.
     *
     * @param id The ID of the team to fetch details for.
     * @return The result of the team detail fetch operation.
     */
    suspend fun getTeamDetail(id: Int) = apiService.getTeamDetail(id)
}