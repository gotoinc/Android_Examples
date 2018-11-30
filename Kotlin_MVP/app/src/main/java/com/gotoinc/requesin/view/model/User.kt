package com.gotoinc.requesin.view.model

import android.os.Parcel
import android.os.Parcelable
import com.gotoinc.requesin.repository.datamodel.UserResponse

/**
 * Created by Illia Derevianko on 28.11.18.
 * GoTo Inc.
 */
data class User(val id: Int, val fullName: String?, val avatar: String?) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(fullName)
        parcel.writeString(avatar)
    }

    override fun describeContents() = 0

    companion object CREATOR : Parcelable.Creator<User> {
        fun from(userResponse: UserResponse) =
            User(userResponse.id, "${userResponse.firstName} ${userResponse.lastName}", userResponse.avatar)

        fun from(userResponses: List<UserResponse>): List<User> {
            return userResponses.map { userResponse -> from(userResponse) }
        }

        override fun createFromParcel(parcel: Parcel): User {
            return User(parcel)
        }

        override fun newArray(size: Int): Array<User?> {
            return arrayOfNulls(size)
        }
    }
}