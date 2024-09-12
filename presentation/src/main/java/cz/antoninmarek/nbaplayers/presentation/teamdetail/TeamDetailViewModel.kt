package cz.antoninmarek.nbaplayers.presentation.teamdetail

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cz.antoninmarek.nbaplayers.domain.model.Team
import cz.antoninmarek.nbaplayers.domain.usecase.GetTeamDetailUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

/**
 * ViewModel for managing the state of the team detail screen.
 *
 * @property teamId The ID of the team to fetch details for.
 * @property getTeamDetailUseCase The use case for fetching the team details.
 */
class TeamDetailViewModel(
    private val teamId: Int,
    private val getTeamDetailUseCase: GetTeamDetailUseCase
) : ViewModel() {
    private val _teamDetail = MutableStateFlow<Team?>(null)
    val teamDetail: StateFlow<Team?> = _teamDetail

    init {
        loadTeamDetail()
    }

    /**
     * Loads the team details and updates the state.
     */
    private fun loadTeamDetail() {
        viewModelScope.launch {
            try {
                val result = getTeamDetailUseCase(teamId)
                _teamDetail.value = result
            } catch (e: Exception) {
                Log.e("TeamDetailViewModel", "Error loading team detail", e)
            }
        }
    }
}