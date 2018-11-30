package com.gotoinc.requesin.view.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gotoinc.requesin.R
import com.gotoinc.requesin.di.DIContainerImpl
import com.gotoinc.requesin.repository.ApiContract
import com.gotoinc.requesin.view.detail.DetailFragment
import com.gotoinc.requesin.view.model.User
import kotlinx.android.synthetic.main.fragment_home.view.*

/**
 * Created by Illia Derevianko on 28.11.18.
 * GoTo Inc.
 */
class HomeFragment : Fragment(), HomeContract {
    private lateinit var adapter: UsersAdapter

    private val queries: ApiContract = DIContainerImpl.queries
    private var nextPage = 1
    private var isExistNextPage = true
    private var isLoading = false

    companion object {
        const val TAG = "HomeFragment"
        fun getInstance() = HomeFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        initList(view)
        if(activity != null)
            queries.getUsersList(nextPage, this, activity as HomeActivity)
        
        return view
    }

    private fun initList(view: View) {
        adapter = UsersAdapter(context, listOf(), callback = {
            fragmentManager?.apply {
                beginTransaction()
                    .add(R.id.container, DetailFragment.getInstance(it), DetailFragment.TAG)
                    .addToBackStack(TAG)
                    .commit()
            }
        })

        view.rv_users.adapter = adapter

        view.rv_users.addOnScrollListener(object: RecyclerView.OnScrollListener() {
            private var lastVisiblePosition = 0

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if(dy > 0) {
                    if(recyclerView.layoutManager != null)
                        lastVisiblePosition = (recyclerView.layoutManager as LinearLayoutManager).findLastVisibleItemPosition()
                    if(adapter.itemCount - 1 - lastVisiblePosition <= 2) {
                        if(isExistNextPage && !isLoading) {
                            isLoading = true
                            if(activity != null)
                                queries.getUsersList(nextPage, this@HomeFragment, activity as HomeActivity)
                        }
                    }
                }
            }
        })
    }

    override fun setUsers(users: List<User>) {
        (users as MutableList).addAll(0, adapter.users)
        val usersDiffUtilCallback = UsersDiffUtilCallback(adapter.users, users)
        val diffResult = DiffUtil.calculateDiff(usersDiffUtilCallback)
        adapter.users = users
        diffResult.dispatchUpdatesTo(adapter)
    }

    override fun nextPage(isExist: Boolean, nextPage: Int) {
        isExistNextPage = isExist
        isLoading = false
        if(isExist) {
            this.nextPage = nextPage
        }
    }
}