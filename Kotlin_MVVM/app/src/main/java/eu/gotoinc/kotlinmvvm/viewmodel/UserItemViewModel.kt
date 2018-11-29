package eu.gotoinc.kotlinmvvm.viewmodel

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import eu.gotoinc.kotlinmvvm.repository.datamodel.response.UserResponse

class UserItemViewModel(@Bindable val user: UserResponse?, val onItemClick: (UserResponse?) -> Unit) : BaseObservable() {
    fun onClick() = onItemClick(user)
}
