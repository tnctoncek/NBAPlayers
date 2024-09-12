package cz.antoninmarek.nbaplayers.presentation.ui

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import cz.antoninmarek.nbaplayers.presentation.R

/**
 * Composable function for displaying a player's image using Glide.
 *
 * @param model The data model to load the image from.
 * @param contentDescription The content description for the image.
 * @param modifier The modifier to be applied to the image.
 */
@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun PlayerImage(
    model: Any,
    contentDescription: String?,
    modifier: Modifier = Modifier
) {
    GlideImage(
        model = model,
        contentDescription = contentDescription,
        modifier = modifier
            .fillMaxWidth()
            .height(240.dp),
        contentScale = ContentScale.FillWidth
    ) {
        it
            .placeholder(R.mipmap.default_player_image)
            .error(R.mipmap.default_player_image)
    }
}