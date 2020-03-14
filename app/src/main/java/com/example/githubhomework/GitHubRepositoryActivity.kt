package com.example.githubhomework

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.githubhomework.ui.repository.GitHubRepositoryFragment
import kotlinx.android.synthetic.main.activity_git_hub_repository.*

class GitHubRepositoryActivity : AppCompatActivity() {
    companion object {
        val ISSUES_URL = "issues_url"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_git_hub_repository)

        val issuesUrl = intent.getStringExtra(ISSUES_URL)!!

        val fragment = fragmentRepository as GitHubRepositoryFragment

        fragment.issuesUrl = issuesUrl
    }
}
