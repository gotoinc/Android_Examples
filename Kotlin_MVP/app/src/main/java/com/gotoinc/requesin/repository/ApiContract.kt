package com.gotoinc.requesin.repository

import com.gotoinc.requesin.view.BaseViewContract
import com.gotoinc.requesin.view.home.HomeContract

/**
 * Created by Illia Derevianko on 28.11.18.
 * GoTo Inc.
 */
interface ApiContract {
    fun getUsersList(page: Int, view: HomeContract, baseView: BaseViewContract)
}