package cz.antoninmarek.nbaplayers.data.model

import cz.antoninmarek.nbaplayers.domain.model.Player

/**
 * Data transfer object representing a player.
 *
 * @property id The unique identifier of the player.
 * @property first_name The first name of the player.
 * @property last_name The last name of the player.
 * @property position The position the player plays.
 * @property height The height of the player.
 * @property weight The weight of the player in pounds.
 * @property jersey_number The jersey number of the player.
 * @property college The college the player attended.
 * @property country The country the player is from.
 * @property draft_year The year the player was drafted.
 * @property draft_round The round in which the player was drafted.
 * @property draft_number The number at which the player was drafted.
 * @property team The team the player belongs to.
 */
data class PlayerDto(
    val id: Int,
    val first_name: String,
    val last_name: String,
    val position: String,
    val height: String,
    val weight: Int,
    val jersey_number: Int,
    val college: String,
    val country: String,
    val draft_year: Int,
    val draft_round: Int,
    val draft_number: Int,
    val team: TeamDto
)

/**
 * Extension function to convert PlayerDto to domain model Player.
 *
 * @return The domain model Player.
 */
public fun PlayerDto.toDomainModel() = Player(
    id = id,
    firstName = first_name,
    lastName = last_name,
    position = position,
    height = height,
    weight = weight,
    jerseyNumber = jersey_number,
    college = college,
    country = country,
    draftYear = draft_year,
    draftRound = draft_round,
    draftNumber = draft_number,
    team = team.toDomainModel()
)