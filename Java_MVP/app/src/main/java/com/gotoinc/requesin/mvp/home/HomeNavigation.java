package com.gotoinc.requesin.mvp.home;

import com.gotoinc.requesin.mvp.common.data_model.User;

import androidx.annotation.NonNull;

/**
 * Created by Illia Derevianko on 01.02.19.
 * GoTo Inc.
 */
interface HomeNavigation {
    void openHomeFragment();

    void openDetailsFragment(@NonNull User user);
}
