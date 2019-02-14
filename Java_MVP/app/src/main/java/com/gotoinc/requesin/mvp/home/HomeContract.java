package com.gotoinc.requesin.mvp.home;

import android.os.Bundle;
import android.os.Parcelable;

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
        void dismissErrorView();

        void saveState(Bundle outState);

        void detachView();

        void destroy();
    }

    interface View extends MvpView {
        void drawState(@NonNull HomeContract.Model model);
    }

    interface Model extends MvpModel, Parcelable {
        boolean isError();
        void setError(boolean flag);
        boolean isErrorShowing();
        void setErrorShowing(boolean flag);
        String getErrorMessage();
        void  setErrorMessage(@NonNull String msg);
        boolean isUsersLoaded();
        @NonNull List<User> getUsers();
        void setUsers(@NonNull List<User> users);
        int getCurrentLoadedPage();
        void setCurrentLoadedPage(@IntRange(from = 1, to = 10) int page);
    }
}
