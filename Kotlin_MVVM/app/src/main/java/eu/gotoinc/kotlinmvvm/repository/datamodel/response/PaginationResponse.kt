package eu.gotoinc.kotlinmvvm.repository.datamodel.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class PaginationResponse<T>(
    @Expose(serialize = false)
    @SerializedName("page")
    val page: Int,

    @Expose(serialize = false)
    @SerializedName("per_page")
    val perPage: Int,

    @Expose(serialize = false)
    @SerializedName("total")
    val total: Int,

    @Expose(serialize = false)
    @SerializedName("total_pages")
    val totalPages: Int,

    @Expose(serialize = false)
    @SerializedName("data")
    val data: T
)