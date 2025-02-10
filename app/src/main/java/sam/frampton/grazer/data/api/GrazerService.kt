package sam.frampton.grazer.data.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import sam.frampton.grazer.data.auth.AuthResponse
import sam.frampton.grazer.data.auth.Credentials
import sam.frampton.grazer.data.users.UsersResponse

interface GrazerService {

    @POST("v1/auth/login")
    suspend fun login(@Body credentials: Credentials): AuthResponse

    @GET("v1/users")
    suspend fun users(@Header("Authorization") authToken: String): UsersResponse

    companion object {
        private const val BASE_URL = "https://grazer.nw.r.appspot.com/"

        fun create(): GrazerService {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit.create(GrazerService::class.java)
        }
    }
}
