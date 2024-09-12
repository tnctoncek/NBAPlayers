package cz.antoninmarek.nbaplayers.presentation.playerlist

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cz.antoninmarek.nbaplayers.domain.model.Player
import cz.antoninmarek.nbaplayers.domain.usecase.GetPlayersUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

/**
 * ViewModel for managing the state of the player list screen.
 *
 * @property getPlayersUseCase The use case for fetching the list of players.
 */
class PlayerListViewModel(
    private val getPlayersUseCase: GetPlayersUseCase
) : ViewModel() {
    private val _players = MutableStateFlow<List<Player>>(emptyList())
    val players: StateFlow<List<Player>> = _players

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private var currentPage = 1
    private val perPage = 35
    private var isLastPage = false

    init {
        loadPlayers()
    }

    /**
     * Loads the list of players and updates the state.
     */
    fun loadPlayers() {
        if (_isLoading.value || isLastPage) return
        _isLoading.value = true

        viewModelScope.launch {
            try {
                Log.d("PlayerListViewModel", "Loading players: page=$currentPage, perPage=$perPage")
                val newPlayers = getPlayersUseCase(currentPage, perPage)
                if (newPlayers.isEmpty()) {
                    isLastPage = true
                } else {
                    _players.value = _players.value + newPlayers
                    currentPage++
                }
                Log.d("PlayerListViewModel", "Loaded ${_players.value.size} players total")
            } catch (e: Exception) {
                Log.e("PlayerListViewModel", "Error loading players", e)
            } finally {
                _isLoading.value = false
            }
        }
    }
}