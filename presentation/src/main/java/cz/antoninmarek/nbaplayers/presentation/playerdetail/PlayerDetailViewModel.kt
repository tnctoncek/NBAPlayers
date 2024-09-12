package cz.antoninmarek.nbaplayers.presentation.playerdetail

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cz.antoninmarek.nbaplayers.domain.model.Player
import cz.antoninmarek.nbaplayers.domain.usecase.GetPlayerDetailUseCase
import cz.antoninmarek.nbaplayers.domain.usecase.GetPlayerImageUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

/**
 * ViewModel for managing the state of the player detail screen.
 *
 * @property playerId The ID of the player whose details are to be fetched.
 * @property getPlayerDetailUseCase The use case for fetching player details.
 * @property getPlayerImageUseCase The use case for fetching the player's image URL.
 */
class PlayerDetailViewModel(
    private val playerId: Int,
    private val getPlayerDetailUseCase: GetPlayerDetailUseCase,
    private val getPlayerImageUseCase: GetPlayerImageUseCase
) : ViewModel() {
    private val _playerState = MutableStateFlow(PlayerDetailState())
    val playerState: StateFlow<PlayerDetailState> = _playerState.asStateFlow()

    init {
        loadPlayerDetail()
    }

    /**
     * Loads the player details and updates the state.
     */
    private fun loadPlayerDetail() {
        viewModelScope.launch {
            _playerState.value = PlayerDetailState(isLoading = true)
            try {
                val player = getPlayerDetailUseCase(playerId)
                _playerState.value = PlayerDetailState(player = player, isLoading = false)
                val imageUrl = getPlayerImageUseCase("${player.firstName} ${player.lastName}")
                _playerState.value = _playerState.value.copy(imageUrl = imageUrl, isLoading = false)
            } catch (e: Exception) {
                Log.e("PlayerDetailViewModel", "Error loading player detail", e)
                _playerState.value = PlayerDetailState(error = e.message ?: "Unknown error occurred")
            }
        }
    }
}

/**
 * Data class representing the state of the player detail screen.
 *
 * @property player The player whose details are being displayed.
 * @property imageUrl The URL of the player's image.
 * @property isLoading Whether the data is currently being loaded.
 * @property error Any error message that occurred during data loading.
 */
data class PlayerDetailState(
    val player: Player? = null,
    val imageUrl: String? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)