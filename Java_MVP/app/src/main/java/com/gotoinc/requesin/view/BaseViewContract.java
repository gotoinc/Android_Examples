package com.gotoinc.requesin.view;

/**
 * Created by Illia Derevianko on 26.11.18.
 * GoTo Inc.
 */
public interface BaseViewContract {
    void onError();
    void onError(String message);
    void onTimeout();
    void onNetworkError();
}
