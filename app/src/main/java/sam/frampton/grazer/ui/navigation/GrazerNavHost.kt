package sam.frampton.grazer.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kotlinx.serialization.Serializable
import sam.frampton.grazer.ui.login.LoginScreen
import sam.frampton.grazer.ui.users.UsersScreen

@Serializable
object Login

@Serializable
object Users

@Composable
fun GrazerNavHost(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Users,
        modifier = modifier
    ) {
        composable<Login> {
            LoginScreen(
                onAuthenticated = { navController.popBackStack() }
            )
        }
        composable<Users> {
            UsersScreen(
                onUnauthenticated = { navController.navigate(Login) },
            )
        }
    }
}
