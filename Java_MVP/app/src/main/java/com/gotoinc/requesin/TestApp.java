package com.gotoinc.requesin;

import android.app.Application;
import android.content.Context;

/**
 * Created by Illia Derevianko on 01.02.19.
 * GoTo Inc.
 */
public class TestApp extends Application {
    private static Context appContext;

    @Override
    public void onCreate() {
        super.onCreate();

        appContext = getApplicationContext();
    }

    public static Context getAppContext() {
        return appContext;
    }
}
