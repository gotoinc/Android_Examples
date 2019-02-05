package com.gotoinc.requesin.mvp.common.callback;

/**
 * Created by Illia Derevianko on 26.11.18.
 * GoTo Inc.
 */
public interface ApiErrorCallback {
    void onError();
    void onError(String message);
    void onTimeout();
    void onNetworkError();
}
