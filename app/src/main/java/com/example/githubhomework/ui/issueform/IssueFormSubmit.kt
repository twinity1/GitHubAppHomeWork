package com.example.githubhomework.ui.issueform

import android.widget.Toast
import com.example.githubhomework.R
import com.example.githubhomework.persistence.entities.Issue
import com.example.githubhomework.persistence.repositories.IssueRepository
import com.example.githubhomework.tools.ErrorMessageHandler
import kotlinx.android.synthetic.main.issue_form_fragment.*

class IssueFormSubmit(private val issueRepository: IssueRepository) {
    fun submit(owner: IssueFormFragment) {
        if (displayErrors(owner) == false) {
            val issue = owner.issue ?: Issue(title = "", body = "")
            issue.title =  owner.viewModel.title.value!!
            issue.body = owner.viewModel.content.value!!

            issueRepository.saveIssue(owner.repositoryFullName, issue) {
                it.fold(
                    onSuccess = {
                        owner.onFinish()
                    },
                    onFailure = {
                        Toast.makeText(owner.requireContext(), ErrorMessageHandler().getStringByException(it, owner.resources), Toast.LENGTH_LONG).show()
                    }
                )
            }
        }
    }

    //returns true if any error was displayed
    fun displayErrors(owner: IssueFormFragment): Boolean
    {
        var result = false

        if (owner.viewModel.title.value == null) {
            result = true
            owner.issueFormTitle.error = owner.resources.getString(R.string.title_required)
        }

        if (owner.viewModel.content.value == null) {
            result = true
            owner.issueFormContent.error = owner.resources.getString(R.string.content_required)
        }

        return result
    }
}