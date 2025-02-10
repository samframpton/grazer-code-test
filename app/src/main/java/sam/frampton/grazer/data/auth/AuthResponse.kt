package sam.frampton.grazer.data.auth

import com.google.gson.annotations.SerializedName

data class AuthResponse(
    val status: String,
    @SerializedName("status_dec") val statusDescription: String,
    val auth: Auth
)

data class Auth(
    val data: AuthData
)

data class AuthData(
    val token: String
)
