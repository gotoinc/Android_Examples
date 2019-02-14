package com.gotoinc.requesin.mvp.home;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import com.gotoinc.requesin.R;
import com.gotoinc.requesin.mvp.common.data_model.User;
import com.gotoinc.requesin.mvp.common.mvp.MvpView;
import com.gotoinc.requesin.repository.RetrofitRequest;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.List;

import androidx.annotation.NonNull;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.HttpException;

/**
 * Created by Illia Derevianko on 01.02.19.
 * GoTo Inc.
 */
public final class HomePresenter implements HomeContract.Presenter {
    private final String EXTRA_MODEL = "MODEL";

    private final Context context;
    private final RetrofitRequest request;
    private HomeContract.View view;
    private HomeContract.Model model;

    private final CheckView<HomeContract.View> checkView;

    private boolean isNextPageExists = true;
    private boolean isLoading;
    private int nextPage = 1;

    private CompositeDisposable disposables = new CompositeDisposable();

    public HomePresenter(@NonNull HomeContract.Model model, RetrofitRequest request, Context appContext) {
        this.context = appContext;
        this.request = request;
        this.model = model;

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
        if(savedState != null) {
            model = savedState.getParcelable(EXTRA_MODEL);
            if(!model.isError() && !model.isUsersLoaded()) {
                load();
                return;
            }
            Log.d("myLog", "load from saved state");
            nextPage = model.getCurrentLoadedPage() + 1;
            checkView.doIt(view, () -> view.drawState(model));
        }
    }

    @Override
    public void load() {
        if(isNextPageExists && !isLoading) {
            Log.d("myLog", "load from API");
            isLoading = true;
            disposables.add(request.getUsersList(nextPage)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe(response -> {
                        Log.d("myLog", "list of users got");
                        usersLoaded(User.from(response.getData()), response.getTotalPages() > nextPage);
                        isLoading = false;
                    }, e -> {
                        e.printStackTrace();
                        if (e instanceof HttpException) onError();
                        else if (e instanceof SocketTimeoutException) onTimeout();
                        else if (e instanceof IOException) onNetworkError();
                        else onError(e.getMessage());
                        isLoading = false;
                    }));
        }
    }

    @Override
    public void dismissErrorView() {
        model.setErrorShowing(false);
    }

    @Override
    public void usersLoaded(List<User> users, boolean isNextPageExists) {
        Log.d("myLog", "users was got in presenter");
        model.setUsers(users);
        model.setCurrentLoadedPage(nextPage);
        nextPage++;
        this.isNextPageExists = isNextPageExists;
        checkView.doIt(view, () -> view.drawState(model));
    }

    @Override
    public void saveState(Bundle outState) {
        Log.d("myLog", "save state");
        outState.putParcelable(EXTRA_MODEL, model);
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
        model.setError(true);
        model.setErrorShowing(true);
        model.setErrorMessage(context.getString(R.string.error_unknown));
        checkView.doIt(view, () -> view.drawState(model));
    }

    @Override
    public void onError(String message) {
        model.setError(true);
        model.setErrorShowing(true);
        model.setErrorMessage(context.getString(R.string.error_unknown));
        checkView.doIt(view, () -> view.drawState(model));
    }

    @Override
    public void onTimeout() {
        model.setError(true);
        model.setErrorShowing(true);
        model.setErrorMessage(context.getString(R.string.error_unknown));
        checkView.doIt(view, () -> view.drawState(model));
    }

    @Override
    public void onNetworkError() {
        model.setError(true);
        model.setErrorShowing(true);
        model.setErrorMessage(context.getString(R.string.error_unknown));
        checkView.doIt(view, () -> view.drawState(model));
    }

    @FunctionalInterface
    interface CheckView<V extends MvpView> {
        void doIt(V v, Runnable r);
    }
}
