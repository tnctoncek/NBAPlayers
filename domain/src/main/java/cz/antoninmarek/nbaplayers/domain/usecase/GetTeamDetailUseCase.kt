package cz.antoninmarek.nbaplayers.domain.usecase

import cz.antoninmarek.nbaplayers.domain.model.Team
import cz.antoninmarek.nbaplayers.domain.repository.TeamRepository

/**
 * Use case for fetching the details of a team.
 *
 * @property teamRepository The repository used to fetch team data.
 */
class GetTeamDetailUseCase(private val teamRepository: TeamRepository) {

    /**
     * Invokes the use case to fetch the details of a team based on the provided team ID.
     *
     * @param id The ID of the team to fetch details for.
     * @return The details of the team.
     */
    suspend operator fun invoke(id: Int): Team {
        return teamRepository.getTeamDetail(id)
    }
}