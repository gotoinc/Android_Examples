package com.gotoinc.requesin.mvp.home;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import com.gotoinc.requesin.mvp.common.data_model.User;

import java.util.List;

import androidx.annotation.NonNull;

/**
 * Created by Illia Derevianko on 01.02.19.
 * GoTo Inc.
 */
public final class HomeModel implements HomeContract.Model {
    private List<User> users;
    private int currentLoadedPage;
    private boolean isError;
    private boolean isErrorShowing;
    private String errorMsg;

    public HomeModel() {
    }

    protected HomeModel(Parcel in) {
        users = in.createTypedArrayList(User.CREATOR);
        currentLoadedPage = in.readInt();
        isError = in.readByte() != 0;
        isErrorShowing = in.readByte() != 0;
        errorMsg = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(users);
        dest.writeInt(currentLoadedPage);
        dest.writeByte((byte) (isError ? 1 : 0));
        dest.writeByte((byte) (isErrorShowing ? 1 : 0));
        dest.writeString(errorMsg);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<HomeModel> CREATOR = new Creator<HomeModel>() {
        @Override
        public HomeModel createFromParcel(Parcel in) {
            return new HomeModel(in);
        }

        @Override
        public HomeModel[] newArray(int size) {
            return new HomeModel[size];
        }
    };

    @Override
    public boolean isError() {
        Log.d("myLog", "get is error state = " + isError);
        return isError;
    }

    @Override
    public void setError(boolean flag) {
        Log.d("myLog", "set error state");
        isError = flag;
    }

    @Override
    public boolean isErrorShowing() {
        Log.d("myLog", "get is error showing = " + isErrorShowing);
        return isErrorShowing;
    }

    @Override
    public void setErrorShowing(boolean flag) {
        Log.d("myLog", "set error showing");
        isErrorShowing = flag;
    }

    @Override
    public String getErrorMessage() {
        Log.d("myLog", "get error message");
        return errorMsg;
    }

    @Override
    public void setErrorMessage(@NonNull String msg) {
        Log.d("myLog", "set error message");
        errorMsg = msg;
    }

    @Override
    public boolean isUsersLoaded() {
        Log.d("myLog", "get is users loaded = " + (users != null));
        return users != null;
    }

    @NonNull
    @Override
    public List<User> getUsers() {
        Log.d("myLog", "get users from model");
        return users;
    }

    @Override
    public void setUsers(@NonNull List<User> users) {
        Log.d("myLog", "set users to model");
        isError = false;
        this.users = users;
    }

    @Override
    public int getCurrentLoadedPage() {
        Log.d("myLog", "get current page from model");
        return currentLoadedPage;
    }

    @Override
    public void setCurrentLoadedPage(int currentLoadedPage) {
        Log.d("myLog", "set current page to model");
        this.currentLoadedPage = currentLoadedPage;
    }

    @Override
    public String toString() {
        return "HomeModel{" +
                "count of users=" + (users != null ? users.size() : null) +
                ", currentLoadedPage=" + currentLoadedPage +
                ", isError=" + isError +
                ", isErrorShowing=" + isErrorShowing +
                ", errorMsg=" + errorMsg +
                '}';
    }
}
