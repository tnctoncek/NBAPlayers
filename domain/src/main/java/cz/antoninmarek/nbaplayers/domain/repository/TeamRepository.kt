package cz.antoninmarek.nbaplayers.domain.repository

import cz.antoninmarek.nbaplayers.domain.model.Team

/**
 * Repository interface for fetching team data.
 */
interface TeamRepository {

    /**
     * Fetches the details of a team based on the provided team ID.
     *
     * @param id The ID of the team to fetch details for.
     * @return The details of the team.
     */
    suspend fun getTeamDetail(id: Int): Team
}