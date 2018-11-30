package com.gotoinc.requesin.view.home

import androidx.recyclerview.widget.DiffUtil
import com.gotoinc.requesin.view.model.User

/**
 * Created by Illia Derevianko on 29.11.18.
 * GoTo Inc.
 */
class UsersDiffUtilCallback(private val oldList: List<User>, private val newList: List<User>) : DiffUtil.Callback() {
    override fun getOldListSize() = oldList.size
    override fun getNewListSize() = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldUser: User = oldList[oldItemPosition]
        val newUser: User = newList[newItemPosition]

        return oldUser.id == newUser.id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldUser: User = oldList[oldItemPosition]
        val newUser: User = newList[newItemPosition]

        return newUser.fullName.equals(oldUser.fullName)
                && newUser.avatar.equals(oldUser.avatar)
    }
}