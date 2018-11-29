package eu.gotoinc.kotlinmvvm.util

import androidx.recyclerview.widget.DiffUtil
import eu.gotoinc.kotlinmvvm.repository.datamodel.response.UserResponse

class UserDiffUtillCallback : DiffUtil.ItemCallback<UserResponse>() {
    override fun areItemsTheSame(oldItem: UserResponse, newItem: UserResponse): Boolean = oldItem == newItem

    override fun areContentsTheSame(oldItem: UserResponse, newItem: UserResponse): Boolean =
        oldItem.avatar == newItem.avatar && oldItem.lastName == newItem.lastName && oldItem.lastName == newItem.lastName

}