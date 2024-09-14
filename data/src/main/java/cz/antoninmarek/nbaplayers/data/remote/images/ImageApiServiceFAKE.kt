package cz.antoninmarek.nbaplayers.data.remote.images

import kotlinx.coroutines.delay
import kotlin.random.Random

/**
 * Fake implementation of the ImageApiService for testing purposes.
 */
class ImageApiServiceFAKE : ImageApiService {

    private val playerImages = mapOf(
        "Jaylen Adams" to "https://cdn.nba.com/headshots/nba/latest/1040x760/1629121.png",
        "Steven Adams" to "https://cdn.nba.com/headshots/nba/latest/1040x760/203500.png",
        "Bam Adebayo" to "https://cdn.nba.com/headshots/nba/latest/1040x760/1628389.png",
        "DeVaughn Akoon-Purcell" to "https://cdn.nba.com/headshots/nba/latest/1040x760/1629152.png",
        "LaMarcus Aldridge" to "https://cdn.nba.com/headshots/nba/latest/1040x760/200746.png",
        "Rawle Alkins" to "https://cdn.nba.com/headshots/nba/latest/1040x760/1628959.png",
        "Grayson Allen" to "https://cdn.nba.com/headshots/nba/latest/1040x760/1628960.png",
        "Jarrett Allen" to "https://cdn.nba.com/headshots/nba/latest/1040x760/1628386.png",
        "Al-Farouq Aminu" to "https://cdn.nba.com/headshots/nba/latest/1040x760/202329.png",
        "Justin Anderson" to "https://cdn.nba.com/headshots/nba/latest/1040x760/1626147.png"
    )

    /**
     * Fetches the image URL of a player based on the provided query.
     *
     * @param query The search player name for the player's image URL.
     * @return The URL of the player's image, or null if not found.
     */
    override suspend fun getPlayerImage(query: String): String? {
        //delay(Random.nextLong(1, 3) * 500)
        return playerImages[query]
    }

}