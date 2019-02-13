package com.gotoinc.requesin.mvp.home;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;

import com.gotoinc.requesin.R;
import com.gotoinc.requesin.mvp.common.data_model.User;
import com.gotoinc.requesin.mvp.common.mvp.MvpView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by Illia Derevianko on 01.02.19.
 * GoTo Inc.
 */
public class HomePresenter implements HomeContract.Presenter {
    private final String EXTRA_USERS = "USERS";
    private final String EXTRA_NEXT_PAGE = "NEXT_PAGE";

    private final Context context;
    private HomeContract.View view;
    private final HomeContract.Model model;
    private final HomeContract.State state;

    private final CheckView<HomeContract.View> checkView;

    private boolean isNextPageExists = true;
    private boolean isLoading;
    private int nextPage = 1;

    private CompositeDisposable disposables = new CompositeDisposable();

    public HomePresenter(@NonNull HomeContract.Model model, @NonNull HomeContract.State state, Context appContext) {
        this.context = appContext;
        this.model = model;
        this.state = state;

        checkView = (view, r) -> {
            if(view != null) r.run();
        };
    }

    @Override
    public void attachView(@NonNull HomeContract.View view) {
        Log.d("myLog", "attach view to presenter");
        this.view = view;
    }

    @SuppressWarnings("ConstantConditions")
    @Override
    public void load(Bundle savedState) {
        if(savedState != null && savedState.containsKey(EXTRA_NEXT_PAGE)) {
            Log.d("myLog", "load from saved state");
            state.setUsers(savedState.getParcelableArrayList(EXTRA_USERS));
            state.setCurrentLoadedPage(savedState.getInt(EXTRA_NEXT_PAGE));
            nextPage = state.getCurrentLoadedPage() + 1;
            checkView.doIt(view, () -> view.showUsers(state.getUsers()));
        } else load();
    }

    @Override
    public void load() {
        if(isNextPageExists && !isLoading) {
            Log.d("myLog", "load from API");
            isLoading = true;
            disposables.add(model.getUsersList(nextPage, this));
        }
    }

    @Override
    public void usersLoaded(List<User> users, boolean isNextPageExists) {
        Log.d("myLog", "users was got in presenter");
        state.setUsers(users);
        state.setCurrentLoadedPage(nextPage);
        nextPage++;
        this.isNextPageExists = isNextPageExists;
        isLoading = false;
        checkView.doIt(view, () -> view.showUsers(users));
    }

    @Override
    public void saveState(Bundle outState) {
        if(state.isUsersLoaded()) {
            outState.putParcelableArrayList(EXTRA_USERS, (ArrayList<? extends Parcelable>) state.getUsers());
            outState.putInt(EXTRA_NEXT_PAGE, state.getCurrentLoadedPage());
        }
    }

    @Override
    public void detachView() {
        Log.d("myLog", "detach view from presenter");
        view = null;
    }

    @Override
    public void destroy() {
        Log.d("myLog", "destroy presenter");
        disposables.clear();
    }

    @Override
    public void onError() {
        checkView.doIt(view, () -> view.showSnackbar(context.getString(R.string.error_unknown)));
    }

    @Override
    public void onError(String message) {
        checkView.doIt(view, () -> view.showSnackbar(message));
    }

    @Override
    public void onTimeout() {
        checkView.doIt(view, () -> view.showSnackbar(context.getString(R.string.error_unknown)));
    }

    @Override
    public void onNetworkError() {
        checkView.doIt(view, () -> view.showSnackbar(context.getString(R.string.error_unknown)));
    }

    @FunctionalInterface
    interface CheckView<V extends MvpView> {
        void doIt(V v, Runnable r);
    }
}
