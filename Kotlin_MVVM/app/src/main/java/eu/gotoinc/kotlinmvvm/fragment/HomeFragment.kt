package eu.gotoinc.kotlinmvvm.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import eu.gotoinc.kotlinmvvm.R
import eu.gotoinc.kotlinmvvm.databinding.FragmentHomeBinding
import eu.gotoinc.kotlinmvvm.di.DIContainerImpl
import eu.gotoinc.kotlinmvvm.util.HOME_VM
import eu.gotoinc.kotlinmvvm.util.architecture.Factory
import eu.gotoinc.kotlinmvvm.viewmodel.HomeScreenViewModel


class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding

    companion object {
        fun newInstance(): HomeFragment = HomeFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        binding.viewModel = ViewModelProviders.of(activity!!, Factory(DIContainerImpl.queries))
            .get(HOME_VM, HomeScreenViewModel::class.java)
        binding.rvUsers.layoutManager = LinearLayoutManager(context)
        return binding.root
    }
}