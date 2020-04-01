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
    val issueRepository: IssueRepository by inject()

    val viewModel: IssueFormViewModel by viewModel()
    private lateinit var binding: IssueFormFragmentBinding

    lateinit var repositoryFullName: String
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

        (requireActivity() as AppCompatActivity).supportActionBar?.title = if (issue == null) requireActivity().resources.getString(R.string.new_issue_title) else requireActivity().resources.getString(R.string.edit_issue_title)

        viewModel.onSubmit = {
            val issue = issue ?: Issue(title = "", body = "")
            issue.title = viewModel.title.value!!
            issue.body = viewModel.content.value!!

            issueRepository.saveIssue(repositoryFullName, issue) {
                it.fold(
                    onSuccess = {
                        onFinish()
                    },
                    onFailure = {
                        Toast.makeText(requireContext(), ErrorMessageHandler().getStringByException(it, resources), Toast.LENGTH_LONG).show()
                    }
                )
            }
        }
    }
}
