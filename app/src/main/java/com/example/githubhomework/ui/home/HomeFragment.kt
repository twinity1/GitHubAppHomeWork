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
import com.example.githubhomework.GitHubUserActivity
import com.example.githubhomework.R
import com.example.githubhomework.components.lists.users.GitHubUserListAdapter
import com.example.githubhomework.databinding.FragmentHomeBinding
import com.example.githubhomework.repositories.GitHubUserRepository
import com.example.githubhomework.tools.ErrorMessageHandler

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel

    private lateinit var binding: FragmentHomeBinding

    private val handler = Handler(Looper.getMainLooper())


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

        homeViewModel.searchText.observe(this, Observer {
            handler.removeCallbacksAndMessages(null)

            if (it.trim() != "") {
                handler.postDelayed({
                    GitHubUserRepository.shared.findByName(it) {
                        it.fold(
                            onSuccess = {
                                homeViewModel.searchResult.value = it
                            },

                            onFailure = {
                                Toast.makeText(activity, activity!!.getString(ErrorMessageHandler().getStringIdByException(it)), Toast.LENGTH_LONG).show()
                            }
                        )
                    }
                }, 300)
            } else {
                homeViewModel.searchResult.value = listOf()
            }
        })

        homeViewModel.searchResult.observe(this, Observer {
            binding.homeRecycleView.apply {
                val userAdapter =
                    GitHubUserListAdapter(
                        it
                    )
                userAdapter.onShowUser = {
                    val intent = Intent(activity, GitHubUserActivity::class.java)

                    intent.putExtra(GitHubUserActivity.EXTRA_GITHUB_REPOSITORY_URL, it.gitHubUser.reposUrl)

                    startActivity(intent)
                }

                layoutManager = LinearLayoutManager(activity)
                adapter = userAdapter
            }
        })
    }
}