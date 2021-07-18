package com.enrique.randomuserapp.ui.users

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.enrique.randomuserapp.databinding.UserItemBinding
import com.enrique.randomuserapp.model.user.UserView

class UsersAdapter : RecyclerView.Adapter<ViewHolder>() {

    var users = mutableListOf<UserView>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(UserItemBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(users[position])
    }

    override fun getItemCount(): Int = users.size

}

class ViewHolder(private val userItemBinding: UserItemBinding) :
    RecyclerView.ViewHolder(userItemBinding.root) {

    fun bind(data: UserView) {
        userItemBinding.tvUserName.text = data.title + " " + data.firstName + " " + data.lastName
        userItemBinding.tvEmail.text = data.email
        userItemBinding.container.setOnClickListener {
            val action = UsersFragmentDirections.actionUserFragmentToDetailsFragment(data.id)
            it.findNavController().navigate(action)
        }
    }

}