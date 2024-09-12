package cz.antoninmarek.nbaplayers.domain.model

/**
 * Represents an NBA player.
 *
 * @property id The unique identifier of the player.
 * @property firstName The first name of the player.
 * @property lastName The last name of the player.
 * @property position The position the player plays.
 * @property height The height of the player.
 * @property weight The weight of the player.
 * @property jerseyNumber The jersey number of the player.
 * @property college The college the player attended.
 * @property country The country the player is from.
 * @property draftYear The year the player was drafted.
 * @property draftRound The round in which the player was drafted.
 * @property draftNumber The overall number at which the player was drafted.
 * @property team The team the player is currently on.
 */
data class Player(
    val id: Int,
    val firstName: String,
    val lastName: String,
    val position: String,
    val height: String,
    val weight: Int,
    val jerseyNumber: Int,
    val college: String,
    val country: String,
    val draftYear: Int,
    val draftRound: Int,
    val draftNumber: Int,
    val team: Team
)