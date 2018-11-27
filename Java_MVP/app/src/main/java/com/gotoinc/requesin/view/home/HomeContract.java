package com.gotoinc.requesin.view.home;

import com.gotoinc.requesin.view._model.User;

import java.util.List;

/**
 * Created by Illia Derevianko on 26.11.18.
 * GoTo Inc.
 */
public interface HomeContract {
    void setUsers(List<User> users);
    void nextPage(boolean isExist, int nextPage);
}
