package com.gotoinc.requesin.repository;

import com.gotoinc.requesin.view.BaseViewContract;
import com.gotoinc.requesin.view.home.HomeContract;

import androidx.annotation.NonNull;

/**
 * Created by Illia Derevianko on 23.11.18.
 * GoTo Inc.
 */
public interface ApiContract {
    void getUsersList(int page, @NonNull HomeContract view, @NonNull BaseViewContract baseView);
}
