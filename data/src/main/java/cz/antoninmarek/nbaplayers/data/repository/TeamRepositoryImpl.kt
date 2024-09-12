package cz.antoninmarek.nbaplayers.data.repository

import cz.antoninmarek.nbaplayers.data.model.toDomainModel
import cz.antoninmarek.nbaplayers.data.remote.nba.NBAApiClient
import cz.antoninmarek.nbaplayers.domain.model.Team
import cz.antoninmarek.nbaplayers.domain.repository.TeamRepository

/**
 * Implementation of the TeamRepository interface.
 *
 * @property apiClient The client used to fetch NBA team data.
 */
class TeamRepositoryImpl(
    private val apiClient: NBAApiClient
) : TeamRepository {

    /**
     * Fetches the details of a team based on the provided team ID.
     *
     * @param id The ID of the team to fetch details for.
     * @return The details of the team.
     */
    override suspend fun getTeamDetail(id: Int): Team {
        return apiClient.getTeamDetail(id).data.toDomainModel()
    }
}