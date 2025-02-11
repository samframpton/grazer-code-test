package sam.frampton.grazer.data.auth

import kotlinx.coroutines.flow.flowOf

class AuthRepositoryFake : AuthRepository {

    var authToken: String? = null
    var email = ""
    var password = ""

    override suspend fun login(email: String, password: String) {
        this.email = email
        this.password = password
    }

    override suspend fun getAuthToken() = flowOf(authToken)

    override suspend fun deleteAuthToken() {
        authToken = null
    }
}
