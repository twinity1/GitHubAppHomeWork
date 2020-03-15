package com.example.githubhomework.ui.repository

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubhomework.IssueActivity

import com.example.githubhomework.R
import com.example.githubhomework.components.lists.issues.IssuesListAdapter
import com.example.githubhomework.databinding.FragmentRepositoryBinding
import com.example.githubhomework.repositories.IssueRepository
import com.example.githubhomework.tools.ErrorMessageHandler
import kotlinx.android.synthetic.main.fragment_repository.*

class RepositoryFragment : Fragment() {
    private lateinit var viewModel: RepositoryViewModel

    private lateinit var binding: FragmentRepositoryBinding

    lateinit var repositoryFullName: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProviders.of(this).get(RepositoryViewModel::class.java)

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_repository, container, false)

        return binding.root
    }


    override fun onStart() {
        super.onStart()

       IssueRepository.shared.findAll(repositoryFullName) {
           it.fold(
               onSuccess = {
                   val adapter = IssuesListAdapter(it)

                   adapter.onIssueShow = {
                       val intent = Intent(activity, IssueActivity::class.java)

                       intent.putExtra(IssueActivity.ISSUE_URL, it.entity.url)

                       startActivity(intent)
                   }

                   repositoryIssueList.layoutManager = LinearLayoutManager(activity)
                   repositoryIssueList.adapter = adapter
               },
               onFailure = {
                    Toast.makeText(activity!!, activity!!.getString(ErrorMessageHandler().getStringIdByException(it)), Toast.LENGTH_LONG).show()
               }
           )
       }
    }
}
