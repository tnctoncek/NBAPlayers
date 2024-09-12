package cz.antoninmarek.nbaplayers.data.model

import cz.antoninmarek.nbaplayers.domain.model.Team

/**
 * Data transfer object representing a team.
 *
 * @property id The unique identifier of the team.
 * @property conference The conference the team belongs to.
 * @property division The division the team belongs to.
 * @property city The city the team is based in.
 * @property name The name of the team.
 * @property full_name The full name of the team.
 * @property abbreviation The abbreviation of the team's name.
 */
data class TeamDto(
    val id: Int,
    val conference: String,
    val division: String,
    val city: String,
    val name: String,
    val full_name: String,
    val abbreviation: String
)

/**
 * Extension function to convert TeamDto to domain model Team.
 *
 * @return The domain model Team.
 */
public fun TeamDto.toDomainModel() = Team(
    id = id,
    conference = conference,
    division = division,
    city = city,
    name = name,
    fullName = full_name,
    abbreviation = abbreviation
)