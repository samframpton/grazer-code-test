package sam.frampton.grazer.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import sam.frampton.grazer.data.auth.AuthRepository
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _loginUiState = MutableStateFlow(LoginUiState())
    val loginUiState = _loginUiState.asStateFlow()

    fun onEmailChanged(email: String) {
        _loginUiState.update { it.copy(email = email) }
    }

    fun onPasswordChanged(password: String) {
        _loginUiState.update { it.copy(password = password) }
    }

    fun onLogin() {
        viewModelScope.launch {
            authRepository.login(
                loginUiState.value.email,
                loginUiState.value.password
            )
            _loginUiState.update { it.copy(isAuthenticated = true) }
        }
    }
}

data class LoginUiState(
    val email: String = "",
    val password: String = "",
    val isAuthenticated: Boolean = false
)
