package com.example.githubhomework

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import com.example.githubhomework.ui.githubuser.GitHubUserFragment

import kotlinx.android.synthetic.main.activity_git_hub_user.*

class GitHubUserActivity : AppCompatActivity() {
    companion object {
        val EXTRA_GITHUB_REPOSITORY_URL = "EXTRA_GITHUB_REPOSITORY_URL"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_git_hub_user)
        setSupportActionBar(toolbar)

        val reposUrl = intent!!.getStringExtra(EXTRA_GITHUB_REPOSITORY_URL)!!

        val fragment = fragmentGitHubUser as GitHubUserFragment

        fragment.reposUrl = reposUrl
    }
}
