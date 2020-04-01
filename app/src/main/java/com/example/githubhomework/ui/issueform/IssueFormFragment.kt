package com.example.githubhomework.ui.issueform

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.githubhomework.R
import com.example.githubhomework.databinding.IssueFormFragmentBinding
import com.example.githubhomework.persistence.entities.Issue
import com.example.githubhomework.persistence.repositories.IssueRepository
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class IssueFormFragment : Fragment() {

    val issueRepository: IssueRepository by inject()

    val viewModel: IssueFormViewModel by viewModel()
    private lateinit var binding: IssueFormFragmentBinding

    lateinit var repositoryFullName: String
    var issue: Issue? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.issue_form_fragment, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        return binding.root
    }

    override fun onStart() {
        super.onStart()

        viewModel.onSubmit = {
            var issue = issue ?: Issue(title = "", body = "")
            issue.title = viewModel.title
            issue.body = viewModel.content

            issueRepository.saveIssue(repositoryFullName, issue) {

            }
        }
    }
}
