package com.example.githubhomework.ui.repository

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.githubhomework.R
import com.example.githubhomework.components.lists.issues.RepositoryIssuesListAdapter
import com.example.githubhomework.databinding.FragmentRepositoryBinding
import com.example.githubhomework.repositories.GitHubRepositoryIssueRepository
import com.example.githubhomework.tools.ErrorMessageHandler
import kotlinx.android.synthetic.main.fragment_repository.*

class GitHubRepositoryFragment : Fragment() {
    private lateinit var viewModel: GitHubRepositoryViewModel

    private lateinit var binding: FragmentRepositoryBinding

    lateinit var repositoryFullName: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProviders.of(this).get(GitHubRepositoryViewModel::class.java)

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_repository, container, false)

        return binding.root
    }


    override fun onStart() {
        super.onStart()

       GitHubRepositoryIssueRepository.shared.findAll(repositoryFullName) {
           it.fold(
               onSuccess = {
                   repositoryIssueList.layoutManager = LinearLayoutManager(activity)
                   repositoryIssueList.adapter = RepositoryIssuesListAdapter(it)

               },
               onFailure = {
                    Toast.makeText(activity!!, activity!!.getString(ErrorMessageHandler().getStringIdByException(it)), Toast.LENGTH_LONG).show()
               }
           )
       }
    }
}
