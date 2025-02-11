package sam.frampton.grazer.data.users

import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import retrofit2.HttpException
import sam.frampton.grazer.data.api.GrazerService
import sam.frampton.grazer.data.auth.AuthRepository
import javax.inject.Inject

interface UsersRepository {

    suspend fun getUsers(): Result<List<User>>
}

class DefaultUsersRepository @Inject constructor(
    private val grazerService: GrazerService,
    private val authRepository: AuthRepository
) : UsersRepository {

    override suspend fun getUsers() =
        try {
            val authToken = authRepository.getAuthToken().filterNotNull().first()
            val usersResponse = grazerService.users("Bearer $authToken")
            Result.success(usersResponse.data.users)
        } catch (e: HttpException) {
            // Delete the auth token if it has expired
            if (e.code() == 401) {
                authRepository.deleteAuthToken()
            }
            Result.failure(e)
        }
}
