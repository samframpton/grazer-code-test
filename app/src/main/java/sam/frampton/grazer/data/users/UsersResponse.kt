package sam.frampton.grazer.data.users

import com.google.gson.annotations.SerializedName

data class UsersResponse(
    val status: String,
    @SerializedName("status_dec") val statusDescription: String,
    val data: UsersData
)

data class UsersData(
    val users: List<User>
)

data class User(
    val name: String,
    @SerializedName("date_of_birth") val dateOfBirth: Int,
    @SerializedName("profile_image") val profileImageUrl: String
)
