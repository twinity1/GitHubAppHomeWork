package com.example.githubhomework

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.githubhomework.ui.issue.IssueFragment
import kotlinx.android.synthetic.main.activity_issue.*

class IssueActivity : AppCompatActivity() {
    companion object {
        val ISSUE_URL = "issue_url"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_issue)

        val issueUrl = intent.getStringExtra(ISSUE_URL)!!

        val fragment = fragmentGitHubIssue as IssueFragment

        fragment.issueUrl = issueUrl
    }
}
