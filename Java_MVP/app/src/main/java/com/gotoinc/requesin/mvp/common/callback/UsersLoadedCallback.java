package com.gotoinc.requesin.mvp.common.callback;

import com.gotoinc.requesin.mvp.common.data_model.User;

import java.util.List;

/**
 * Created by Illia Derevianko on 01.02.19.
 * GoTo Inc.
 */
public interface UsersLoadedCallback extends ApiErrorCallback {
    void usersLoaded(List<User> users, boolean isNextPageExists);
}
