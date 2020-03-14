package com.example.githubhomework

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.githubhomework.ui.issue.GitHubIssueFragment
import kotlinx.android.synthetic.main.activity_git_hub_issue.*

class GitHubIssueActivity : AppCompatActivity() {
    companion object {
        val ISSUE_URL = "issue_url"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_git_hub_issue)

        val issueUrl = intent.getStringExtra(ISSUE_URL)!!

        val fragment = fragmentGitHubIssue as GitHubIssueFragment

        fragment.issueUrl = issueUrl
    }
}
