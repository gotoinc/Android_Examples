package com.gotoinc.requesin.repository.datamodel

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by Illia Derevianko on 28.11.18.
 * GoTo Inc.
 */
data class PaginationResponse<T>(
    @Expose(serialize = false) @SerializedName("page") val page: Int
    , @Expose(serialize = false) @SerializedName("per_page") val perPage: Int
    , @Expose(serialize = false) @SerializedName("total") val total: Int
    , @Expose(serialize = false) @SerializedName("total_pages") val totalPages: Int
    , @Expose(serialize = false) @SerializedName(value = "data", alternate = []) val data: T
)

data class UserResponse(
    @Expose(serialize = false) @SerializedName("id") val id: Int
    , @Expose(serialize = false) @SerializedName("first_name") val firstName: String
    , @Expose(serialize = false) @SerializedName("last_name") val lastName: String
    , @Expose(serialize = false) @SerializedName("avatar") val avatar: String
)