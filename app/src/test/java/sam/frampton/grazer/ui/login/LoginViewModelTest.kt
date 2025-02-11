package sam.frampton.grazer.ui.login

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import sam.frampton.grazer.MainDispatcherRule
import sam.frampton.grazer.data.auth.AuthRepositoryFake

class OnboardingViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Test
    fun testOnEmailChanged() {
        val viewModel = LoginViewModel(AuthRepositoryFake())
        val email = "test@test.com"

        viewModel.onEmailChanged(email)

        assertEquals(email, viewModel.loginUiState.value.email)
    }

    @Test
    fun testOnPasswordChanged() {
        val viewModel = LoginViewModel(AuthRepositoryFake())
        val password = "password"

        viewModel.onPasswordChanged(password)

        assertEquals(password, viewModel.loginUiState.value.password)
    }

    @Test
    fun testOnLogin() {
        val authRepositoryFake = AuthRepositoryFake()
        val viewModel = LoginViewModel(authRepositoryFake)
        val email = "test@test.com"
        val password = "password"

        viewModel.onEmailChanged(email)
        viewModel.onPasswordChanged(password)
        viewModel.onLogin()

        assertEquals(email, authRepositoryFake.email)
        assertEquals(password, authRepositoryFake.password)
        assertTrue(viewModel.loginUiState.value.isAuthenticated)
    }
}
