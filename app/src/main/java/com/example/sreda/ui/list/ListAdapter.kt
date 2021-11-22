package com.example.sreda.ui.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.sreda.data.User
import com.example.sreda.databinding.ItemUserBinding

/**
 * @author s.timirov
 */
class   ListAdapter : RecyclerView.Adapter<ListAdapter.ViewHolder>() {

    var userList: List<User> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemUserBinding.inflate(inflater)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user = userList[position]
        holder.bind(user)
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    inner class ViewHolder(private val binding: ItemUserBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(user: User) {
            binding.email.text = user.email
            binding.firstName.text = user.firstName
            binding.lastName.text = user.lastName

            Glide.with(binding.avatar)
                .load(user.avatarUrl)
                .into(binding.avatar)
        }
    }
}
