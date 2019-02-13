package com.gotoinc.requesin.mvp.home;

import android.os.Bundle;

import com.gotoinc.requesin.mvp.common.callback.UsersLoadedCallback;
import com.gotoinc.requesin.mvp.common.data_model.User;
import com.gotoinc.requesin.mvp.common.mvp.MvpModel;
import com.gotoinc.requesin.mvp.common.mvp.MvpPresenter;
import com.gotoinc.requesin.mvp.common.mvp.MvpState;
import com.gotoinc.requesin.mvp.common.mvp.MvpView;

import java.util.List;

import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import io.reactivex.disposables.Disposable;

/**
 * Created by Illia Derevianko on 26.11.18.
 * GoTo Inc.
 */
public interface HomeContract {
    interface Presenter extends MvpPresenter, UsersLoadedCallback {
        void attachView(@NonNull HomeContract.View view);

        void load(Bundle savedState);
        void load();

        void saveState(Bundle outState);

        void detachView();

        void destroy();
    }

    interface View extends MvpView {
        void showUsers(@NonNull List<User> users);
        void showSnackbar(@NonNull String message);
    }

    interface State extends MvpState {
        boolean isUsersLoaded();
        @NonNull List<User> getUsers();
        void setUsers(@NonNull List<User> users);
        int getCurrentLoadedPage();
        void setCurrentLoadedPage(@IntRange(from = 1, to = 10) int page);
    }

    interface Model extends MvpModel {
        Disposable getUsersList(int page, @NonNull UsersLoadedCallback callback);
    }
}
