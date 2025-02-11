package sam.frampton.grazer.ui.users

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import sam.frampton.grazer.R
import sam.frampton.grazer.data.users.User

@Composable
fun UsersScreen(
    onUnauthenticated: () -> Unit,
    viewModel: UsersViewModel = hiltViewModel()
) {
    val uiState by viewModel.usersUiState.collectAsStateWithLifecycle()

    when {
        uiState.isAuthenticated == true -> {
            UsersList(
                users = uiState.users,
                onLogOut = viewModel::onLogout
            )
        }

        uiState.isAuthenticated == false -> {
            LaunchedEffect(Unit) {
                onUnauthenticated()
            }
        }

        uiState.isLoading -> Loading()
    }
}

@Composable
private fun Loading() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(modifier = Modifier.width(64.dp))
    }
}

@Composable
private fun UsersList(
    users: List<User>,
    onLogOut: () -> Unit
) {
    Column(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            modifier = Modifier
                .padding(top = 8.dp),
        ) {
            items(users) { user ->
                UserListItem(user = user)
            }
            item {
                Button(
                    onClick = onLogOut,
                    modifier = Modifier
                        .weight(1f)
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                        .fillMaxWidth()
                ) {
                    Text(text = stringResource(id = R.string.logout_label))
                }
            }
        }
    }
}

@Composable
private fun UserListItem(user: User) {
    Row(
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            model = user.profileImageUrl,
            contentDescription = null,
            modifier = Modifier.size(64.dp)
        )
        Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
            Text(text = user.name)
            Text(text = user.dateOfBirth)
        }
    }
}
