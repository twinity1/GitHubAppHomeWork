package com.example.githubhomework.ui.githubuser

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.example.githubhomework.GitHubUserActivity
import com.example.githubhomework.R
import com.example.githubhomework.databinding.FragmentGitHubUserBinding
import com.example.githubhomework.databinding.FragmentHomeBinding
import com.example.githubhomework.ui.home.HomeViewModel

class GitHubUserFragment : Fragment() {

    private lateinit var viewModel: GitHubUserViewModel

    private lateinit var binding: FragmentGitHubUserBinding

    lateinit var reposUrl: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProviders.of(this).get(GitHubUserViewModel::class.java)

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_git_hub_user, container, false)

        binding.viewModel = viewModel

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}
