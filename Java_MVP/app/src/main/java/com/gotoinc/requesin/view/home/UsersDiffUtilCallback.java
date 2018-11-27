package com.gotoinc.requesin.view.home;

import com.gotoinc.requesin.view._model.User;

import java.util.List;

import androidx.recyclerview.widget.DiffUtil;

/**
 * Created by Illia Derevianko on 27.11.18.
 * GoTo Inc.
 */
public class UsersDiffUtilCallback extends DiffUtil.Callback {
    private final List<User> oldList;
    private final List<User> newList;

    public UsersDiffUtilCallback(List<User> oldList, List<User> newList) {
        this.oldList = oldList;
        this.newList = newList;
    }

    @Override
    public int getOldListSize() {
        return oldList.size();
    }

    @Override
    public int getNewListSize() {
        return newList.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        User oldUser = oldList.get(oldItemPosition);
        User newUser = newList.get(newItemPosition);
        return oldUser.getId() == newUser.getId();
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        User oldUser = oldList.get(oldItemPosition);
        User newUser = newList.get(newItemPosition);
        return newUser.getFullname().equals(oldUser.getFullname())
                && newUser.getAvatar().equals(oldUser.getAvatar());
    }
}
