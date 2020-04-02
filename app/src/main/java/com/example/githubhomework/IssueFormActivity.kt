package com.example.githubhomework

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import android.widget.Toast
import com.example.githubhomework.persistence.repositories.IssueRepository
import com.example.githubhomework.ui.issueform.IssueFormFragment
import kotlinx.android.synthetic.main.issue_form_activity.*
import org.koin.android.ext.android.inject

class IssueFormActivity : AppCompatActivity() {
    companion object {
        val FULL_REPOSITORY_NAME = "full_repository_name"
        val ISSUE_URL = "issue_url"
        val SUCCESS = 100
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.issue_form_activity)

        val repoName = intent.getStringExtra(FULL_REPOSITORY_NAME)!!
        val issueUrl = intent.getStringExtra(ISSUE_URL)

        val issueFormFragment = issueFragment as IssueFormFragment

        issueFormFragment.issueUrl = issueUrl
        issueFormFragment.repositoryFullName = repoName
        issueFormFragment.onFinish = {
            setResult(SUCCESS)
            finish()
        }
    }

    override fun onStart() {
        super.onStart()
    }
}
