package com.example.githubhomework.ui.home

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubhomework.UserActivity
import com.example.githubhomework.R
import com.example.githubhomework.components.lists.users.UserListAdapter
import com.example.githubhomework.databinding.FragmentHomeBinding
import com.example.githubhomework.myModule
import com.example.githubhomework.repositories.UserRepository
import com.example.githubhomework.tools.ErrorMessageHandler
import org.koin.android.ext.android.inject

class HomeFragment : Fragment() {

    lateinit var homeViewModel: HomeViewModel

    lateinit var binding: FragmentHomeBinding

    private val searchObserver: SearchObserver by inject()
    private val recycleViewObserver: RecycleViewObserver by inject()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)

        binding.viewModel = homeViewModel

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        homeViewModel.searchText.observe(viewLifecycleOwner, searchObserver.create(this))
        homeViewModel.searchResult.observe(viewLifecycleOwner, recycleViewObserver.create(this))
    }
}