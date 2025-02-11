package sam.frampton.grazer.data.auth

import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import sam.frampton.grazer.MainDispatcherRule
import sam.frampton.grazer.data.api.GrazerServiceFake
import sam.frampton.grazer.data.users.DefaultUsersRepository
import sam.frampton.grazer.data.users.User
import sam.frampton.grazer.data.users.UsersData
import sam.frampton.grazer.data.users.UsersResponse

class UsersRepositoryTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Test
    fun testGetUsers(): Unit = runTest {
        val authToken = "token"
        val authRepositoryFake = AuthRepositoryFake()
        authRepositoryFake.authToken = authToken

        val usersResponse = UsersResponse(
            status = "200",
            statusDescription = "OK",
            data = UsersData(
                users = listOf(
                    User(
                        name = "Kimberly Carpenter",
                        dateOfBirth = "978307200",
                        profileImageUrl = "https://api.multiavatar.com/kimberly.png"
                    )
                )
            )
        )
        val grazerServiceFake = GrazerServiceFake()
        grazerServiceFake.usersResponse = usersResponse

        val repository = DefaultUsersRepository(grazerServiceFake, authRepositoryFake)

        val users = repository.getUsers()

        assertEquals("Bearer $authToken", grazerServiceFake.authToken)
        assertEquals(usersResponse.data.users, users.getOrNull())
    }
}
