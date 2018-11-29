package eu.gotoinc.kotlinmvvm.adapter

import androidx.databinding.BaseObservable
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import eu.gotoinc.kotlinmvvm.BR

class ViewHolder(private val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(baseViewModel: BaseObservable) {
        binding.apply {
            setVariable(BR.viewModel, baseViewModel)
            executePendingBindings()
        }
    }

}