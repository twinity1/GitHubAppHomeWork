package com.example.githubhomework.ui.home

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.githubhomework.R
import com.example.githubhomework.databinding.FragmentHomeListBinding
import com.example.githubhomework.entities.GitHubUser

class UserAdapter(private val users: List<GitHubUser>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private lateinit var layoutInflater: LayoutInflater

    var onShowUser: ((HomeListViewModel) -> Unit)? = null

    class UserViewHolder(itemView: View, val binding: FragmentHomeListBinding) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (::layoutInflater.isInitialized == false) {
            layoutInflater = LayoutInflater.from(parent.context)
        }

        val binding = DataBindingUtil.inflate<FragmentHomeListBinding>(layoutInflater, R.layout.fragment_home_list, parent, false);

        return UserViewHolder(binding.root, binding)
    }

    override fun getItemCount(): Int {
        return users.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val holder = holder as UserViewHolder

        val viewModel = HomeListViewModel(users[position])

        viewModel.onShowUser = {
            onShowUser?.let { it(viewModel) }
        }

        holder.binding.viewModel = viewModel
    }
}