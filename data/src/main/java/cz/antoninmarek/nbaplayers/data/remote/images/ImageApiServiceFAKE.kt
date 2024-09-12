package cz.antoninmarek.nbaplayers.data.remote.images

import kotlinx.coroutines.delay
import kotlin.random.Random

/**
 * Fake implementation of the ImageApiService for testing purposes.
 */
class ImageApiServiceFAKE : ImageApiService {

    private val playerImages = mapOf(
        "Jaylen Adams" to "https://www.proballers.com/media/cache/torso_player/ul/player/jaylen-adams-1ee687e0-8d27-6bf0-b58d-5bf0a20e7f17.jpg",
        "Steven Adams" to "https://cdn.nba.com/headshots/nba/latest/1040x760/203500.png",
        "Bam Adebayo" to "https://heatnation.com/wp-content/uploads/2020/01/USATSI_13818512-e1578595046659.jpg",
        "DeVaughn Akoon-Purcell" to "https://vtb-league.com/app/uploads/2024/06/53703174661_aa7edef0f1_k.jpg",
        "LaMarcus Aldridge" to "https://people.com/thmb/6f63W8imMYSKaKxC7tn6twUQA7U=/1500x0/filters:no_upscale():max_bytes(150000):strip_icc():focal(539x0:541x2)/LaMarcus-Aldridge2-7f4ad6b112a644e18d884c9e913ceaf8.jpg",
        "Rawle Alkins" to "https://www.azcentral.com/gcdn/-mm-/0b8b97fe1cd2e340f4498f8e34ebf5d72c68f85a/c=0-0-3303-1866/local/-/media/2017/05/21/Phoenix/Phoenix/636309894583302155-alkins.jpg",
        "Grayson Allen" to "https://thespun.com/.image/t_share/MTgzMTI4MjYwNzE5MDI3NTUy/la-clippers-v-memphis-grizzlies.jpg",
        "Jarrett Allen" to "https://encrypted-tbn0.gstatic.com/licensed-image?q=tbn:ANd9GcTm4NiBoqGtZSA6vO2KAShtfdf8HkVyc5j5RrVx_uTDNDKDq7tj5uk6xXolcE3HX8ZoT5r9ggrQ8A4Kk04",
        "Al-Farouq Aminu" to "https://cdn.vox-cdn.com/thumbor/Uv0NB7ykE5Bj7ihgnnW9ppsBGsc=/1400x1400/filters:format(jpeg)/cdn.vox-cdn.com/uploads/chorus_asset/file/19253171/1173104057.jpg.jpg",
        "Justin Anderson" to "https://e0.365dm.com/20/07/2048x1152/skysports-justin-anderson-long-island-nets_5043449.jpg?20200719074958"
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