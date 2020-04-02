package com.example.githubhomework.ui.issueform

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.githubhomework.R
import com.example.githubhomework.databinding.IssueFormFragmentBinding
import com.example.githubhomework.persistence.entities.Issue
import com.example.githubhomework.persistence.repositories.IssueRepository
import com.example.githubhomework.tools.ErrorMessageHandler
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class IssueFormFragment : Fragment() {
    val issueFormSubmit: IssueFormSubmit by inject()
    val issueRepository: IssueRepository by inject()

    val viewModel: IssueFormViewModel by viewModel()
    private lateinit var binding: IssueFormFragmentBinding

    lateinit var repositoryFullName: String
    var issueUrl: String? = null

    var issue: Issue? = null

    var onFinish = {}

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

        val bar = (requireActivity() as AppCompatActivity).supportActionBar

        if (issueUrl != null) {
            issueRepository.findSingle(issueUrl!!) {
                it.fold(
                    onSuccess = {
                        issue = it
                        viewModel.title.value = it.title
                        viewModel.content.value = it.body
                    },
                    onFailure = {
                        Toast.makeText(requireContext(), resources.getString(R.string.issue_not_found), Toast.LENGTH_SHORT).show()
                        requireActivity().finish()
                    }
                )
            }
        }

        bar?.title = if (issueUrl == null) requireActivity().resources.getString(R.string.new_issue_title) else requireActivity().resources.getString(R.string.edit_issue_title)

        viewModel.onSubmit = {
            issueFormSubmit.submit(this)
        }
    }
}
