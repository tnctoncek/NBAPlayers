package cz.antoninmarek.nbaplayers.data.remote.nba

import cz.antoninmarek.nbaplayers.data.model.PlayerDto
import cz.antoninmarek.nbaplayers.data.model.TeamDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Service interface for fetching NBA data from a remote source.
 */
interface ApiService {

    /**
     * Fetches a list of players based on the provided pagination parameters.
     *
     * @param page The page number to fetch.
     * @param perPage The number of players per page.
     * @return The response containing a list of players.
     */
    @GET("players")
    suspend fun getPlayers(@Query("cursor") page: Int, @Query("per_page") perPage: Int): PlayersResponse

    /**
     * Fetches the details of a player based on the provided player ID.
     *
     * @param id The ID of the player to fetch details for.
     * @return The response containing the player's details.
     */
    @GET("players/{id}")
    suspend fun getPlayerDetail(@Path("id") id: Int): PlayerDetailResponse

    /**
     * Fetches the details of a team based on the provided team ID.
     *
     * @param id The ID of the team to fetch details for.
     * @return The response containing the team's details.
     */
    @GET("teams/{id}")
    suspend fun getTeamDetail(@Path("id") id: Int): TeamDetailResponse
}

/**
 * Response containing a list of players.
 *
 * @property data The list of player data transfer objects.
 */
data class PlayersResponse(val data: List<PlayerDto>)

/**
 * Response containing the details of a player.
 *
 * @property data The player data transfer object.
 */
data class PlayerDetailResponse(val data: PlayerDto)

/**
 * Response containing the details of a team.
 *
 * @property data The team data transfer object.
 */
data class TeamDetailResponse(val data: TeamDto)