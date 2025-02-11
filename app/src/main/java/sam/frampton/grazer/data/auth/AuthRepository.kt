package sam.frampton.grazer.data.auth

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import sam.frampton.grazer.data.api.GrazerService
import javax.inject.Inject

private val AUTH_TOKEN_KEY = stringPreferencesKey("auth_token")

interface AuthRepository {

    suspend fun login(email: String, password: String)

    suspend fun getAuthToken(): Flow<String?>

    suspend fun deleteAuthToken()
}

class AuthDataStoreRepository @Inject constructor(
    private val grazerService: GrazerService,
    private val dataStore: DataStore<Preferences>
) : AuthRepository {

    override suspend fun login(email: String, password: String) {
        val credentials = Credentials(email, password)
        val authResponse = grazerService.login(credentials)
        dataStore.edit { it[AUTH_TOKEN_KEY] = authResponse.auth.data.token }
    }

    override suspend fun getAuthToken() = dataStore.data.map { it[AUTH_TOKEN_KEY] }

    override suspend fun deleteAuthToken() {
        dataStore.edit { it.remove(AUTH_TOKEN_KEY) }
    }
}
