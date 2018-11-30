package com.gotoinc.requesin.view.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.gotoinc.requesin.R
import com.gotoinc.requesin.view.model.User
import com.squareup.picasso.Picasso

/**
 * Created by Illia Derevianko on 29.11.18.
 * GoTo Inc.
 */
class UsersAdapter(val context: Context?, var users: List<User>, val callback: (user: User) -> Unit) : RecyclerView.Adapter<UsersAdapter.UserHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserHolder {
        return UserHolder(LayoutInflater.from(context).inflate(R.layout.item_user, parent, false))
    }

    override fun getItemCount(): Int {
        return users.size
    }

    override fun onBindViewHolder(holder: UserHolder, position: Int) {
        Picasso.get().load(users[position].avatar).fit().centerCrop().into(holder.image)
        holder.txtFullname.text = users[position].fullName
    }

    inner class UserHolder(v: View) : RecyclerView.ViewHolder(v) {
        val root: CardView = v.findViewById(R.id.root)
        val image: AppCompatImageView = v.findViewById(R.id.image)
        val txtFullname: AppCompatTextView = v.findViewById(R.id.txt_fullname)

        init {
            root.setOnClickListener { callback(users[adapterPosition]) }
        }
    }
}