package cz.antoninmarek.nbaplayers.domain.model

/**
 * Represents an NBA team.
 *
 * @property id The unique identifier of the team.
 * @property conference The conference the team belongs to.
 * @property division The division the team belongs to.
 * @property city The city the team is based in.
 * @property name The name of the team.
 * @property fullName The full name of the team.
 * @property abbreviation The abbreviation of the team's name.
 */
data class Team(
    val id: Int,
    val conference: String,
    val division: String,
    val city: String,
    val name: String,
    val fullName: String,
    val abbreviation: String
)