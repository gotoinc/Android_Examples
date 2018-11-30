package com.gotoinc.requesin.view.home

import com.gotoinc.requesin.view.model.User

/**
 * Created by Illia Derevianko on 28.11.18.
 * GoTo Inc.
 */
interface HomeContract {
    fun setUsers(users: List<User>)
    fun nextPage(isExist: Boolean, nextPage: Int)
}