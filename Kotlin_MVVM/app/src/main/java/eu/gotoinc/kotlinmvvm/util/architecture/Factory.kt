@file:Suppress("UNCHECKED_CAST")

package eu.gotoinc.kotlinmvvm.util.architecture

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import eu.gotoinc.kotlinmvvm.repository.ApiContract
import eu.gotoinc.kotlinmvvm.viewmodel.HomeScreenViewModel

class Factory(private val api: ApiContract) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when(modelClass){
            HomeScreenViewModel::class.java -> HomeScreenViewModel(api) as T
            else -> throw IllegalArgumentException("ViewModel Not Found")
        }
    }

}