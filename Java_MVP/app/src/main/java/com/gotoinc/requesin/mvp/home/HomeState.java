package com.gotoinc.requesin.mvp.home;

import com.gotoinc.requesin.mvp.common.data_model.User;

import java.util.List;

import androidx.annotation.NonNull;

/**
 * Created by Illia Derevianko on 04.02.19.
 * GoTo Inc.
 */
public class HomeState implements HomeContract.State {
    private List<User> users;
    private int currentLoadedPage;

    @Override
    public boolean isUsersLoaded() {
        return users != null;
    }

    @NonNull
    @Override
    public List<User> getUsers() {
        return users;
    }

    @Override
    public void setUsers(@NonNull List<User> users) {
        this.users = users;
    }

    @Override
    public int getCurrentLoadedPage() {
        return currentLoadedPage;
    }

    @Override
    public void setCurrentLoadedPage(int currentLoadedPage) {
        this.currentLoadedPage = currentLoadedPage;
    }
}
