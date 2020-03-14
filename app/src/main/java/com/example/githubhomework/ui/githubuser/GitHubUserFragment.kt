package com.example.githubhomework.ui.githubuser

import android.app.Activity
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubhomework.R
import com.example.githubhomework.databinding.FragmentGitHubUserBinding
import com.example.githubhomework.repositories.GitHubRepositoryRepository
import com.example.githubhomework.repositories.GitHubUserRepository
import kotlinx.android.synthetic.main.fragment_git_hub_user.*

class GitHubUserFragment : Fragment() {

    private lateinit var viewModel: GitHubUserViewModel

    private lateinit var binding: FragmentGitHubUserBinding

    lateinit var reposUrl: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProviders.of(this).get(GitHubUserViewModel::class.java)

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_git_hub_user, container, false)

        binding.viewModel = viewModel

        viewModel.repositoryList.observe(this, Observer {
            gitHubUserList.layoutManager = LinearLayoutManager(activity)
            gitHubUserList.adapter = RepositoryListAdapter(it)
        })

        return binding.root
    }

    override fun onStart() {
        super.onStart()

        GitHubRepositoryRepository.shared.findAllByReposUrl(reposUrl) {
            if (it != null) {
                viewModel.repositoryList.value = it
            }
        }

    }
}
