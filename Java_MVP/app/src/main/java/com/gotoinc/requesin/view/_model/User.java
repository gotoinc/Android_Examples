package com.gotoinc.requesin.view._model;

import android.os.Parcel;
import android.os.Parcelable;

import com.gotoinc.requesin.repository.data_models.responses.UserResponse;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;

/**
 * Created by Illia Derevianko on 26.11.18.
 * GoTo Inc.
 */
public class User implements Parcelable {
    private int id;
    private String firstName;
    private String lastName;
    private String avatar;

    public User(int id, String firstName, String lastName, String avatar) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.avatar = avatar;
    }

    protected User(Parcel in) {
        id = in.readInt();
        firstName = in.readString();
        lastName = in.readString();
        avatar = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(firstName);
        dest.writeString(lastName);
        dest.writeString(avatar);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    public int getId() {
        return id;
    }

    @NonNull
    public String getFullname() {
        return firstName + " " + lastName;
    }

    @NonNull
    public String getAvatar() {
        return avatar;
    }



    public static User from(@NonNull UserResponse response) {
        return new User(response.getId(), response.getFirstName(), response.getLastName(), response.getAvatar());
    }

    public static List<User> from(List<UserResponse> responses) {
        List<User> users = new ArrayList<>(responses.size());
        for(UserResponse response : responses) {
            users.add(from(response));
        }

        return users;
    }
}
