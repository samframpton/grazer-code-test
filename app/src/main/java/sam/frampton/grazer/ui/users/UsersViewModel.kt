package sam.frampton.grazer.ui.users

import android.text.format.DateFormat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import sam.frampton.grazer.data.auth.AuthRepository
import sam.frampton.grazer.data.users.User
import sam.frampton.grazer.data.users.UsersRepository
import javax.inject.Inject

@HiltViewModel
class UsersViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val usersRepository: UsersRepository
) : ViewModel() {

    private val _usersUiState = MutableStateFlow(UsersUiState())
    val usersUiState = _usersUiState.asStateFlow()

    init {
        viewModelScope.launch {
            authRepository.getAuthToken().map { it != null }.collect { isAuthenticated ->
                val users: List<User> = if (isAuthenticated) {
                    _usersUiState.update { it.copy(isLoading = true) }
                    usersRepository.getUsers()
                        .getOrDefault(emptyList())
                        .map { it.copy(dateOfBirth = formatTimestamp(it.dateOfBirth.toLong())) }
                } else {
                    emptyList()
                }
                _usersUiState.update {
                    it.copy(
                        users = users,
                        isLoading = false,
                        isAuthenticated = isAuthenticated
                    )
                }
            }
        }
    }

    private fun formatTimestamp(timestamp: Long) =
        DateFormat.format("dd-MM-yyyy", timestamp * 1000).toString()

    fun onLogout() {
        viewModelScope.launch {
            authRepository.deleteAuthToken()
        }
    }
}

data class UsersUiState(
    val users: List<User> = emptyList(),
    val isLoading: Boolean = false,
    val isAuthenticated: Boolean? = null
)
