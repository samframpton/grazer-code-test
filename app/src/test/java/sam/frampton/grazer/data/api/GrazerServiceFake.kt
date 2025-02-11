package sam.frampton.grazer.data.api

import sam.frampton.grazer.data.auth.AuthResponse
import sam.frampton.grazer.data.auth.Credentials
import sam.frampton.grazer.data.users.UsersResponse

class GrazerServiceFake : GrazerService {

    var authToken: String? = null
    var usersResponse: UsersResponse? = null

    override suspend fun login(credentials: Credentials): AuthResponse {
        TODO("Not yet implemented")
    }

    override suspend fun users(authToken: String): UsersResponse {
        this.authToken = authToken
        return usersResponse!!
    }
}
