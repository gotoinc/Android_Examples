package eu.gotoinc.kotlinmvvm.repository.datamodel.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class UserResponse(
    @Expose(serialize = false)
    @SerializedName("id")
    val id: String,

    @Expose(serialize = false)
    @SerializedName("first_name")
    val firstName: String,

    @Expose(serialize = false)
    @SerializedName("last_name")
    val lastName: String,

    @Expose(serialize = false)
    @SerializedName("avatar")
    val avatar: String
) {
    fun getFullName(): String = "$firstName $lastName"
}