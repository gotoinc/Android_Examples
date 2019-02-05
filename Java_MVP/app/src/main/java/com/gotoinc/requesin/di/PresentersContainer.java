package com.gotoinc.requesin.di;

import com.gotoinc.requesin.mvp.common.mvp.MvpPresenter;

import androidx.annotation.NonNull;

/**
 * Created by Illia Derevianko on 23.11.18.
 * GoTo Inc.
 */
interface PresentersContainer {
    <T extends MvpPresenter> T of(@NonNull String tag, @NonNull Class<T> mvpPresenter);
    void release(@NonNull String tag);
}
