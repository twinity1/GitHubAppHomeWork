package com.example.githubhomework.ui.githubuser

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubhomework.R
import com.example.githubhomework.GitHubRepositoryActivity
import com.example.githubhomework.components.lists.repositories.RepositoryListAdapter
import com.example.githubhomework.databinding.FragmentGitHubUserBinding
import com.example.githubhomework.repositories.GitHubRepositoryRepository
import com.example.githubhomework.tools.ErrorMessageHandler
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
            val repositoryListAdapter =
                RepositoryListAdapter(
                    it
                )

            gitHubUserList.layoutManager = LinearLayoutManager(activity)
            gitHubUserList.adapter = repositoryListAdapter

            repositoryListAdapter.onShowRepository = {
                val intent = Intent(activity, GitHubRepositoryActivity::class.java)

                intent.putExtra(GitHubRepositoryActivity.REPOSITORY_FULLNAME_URL, it.entity.fullName)

                startActivity(intent)
            }
        })



        return binding.root
    }

    override fun onStart() {
        super.onStart()

        GitHubRepositoryRepository.shared.findAllByReposUrl(reposUrl) {
            it.fold(
                onSuccess = {
                    viewModel.repositoryList.value = it

                },
                onFailure = {
                    Toast.makeText(activity, activity!!.getString(ErrorMessageHandler().getStringIdByException(it)), Toast.LENGTH_LONG).show()
                }
            )
        }

    }
}
