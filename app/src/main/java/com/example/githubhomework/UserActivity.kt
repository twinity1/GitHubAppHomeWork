package com.example.githubhomework

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.githubhomework.ui.user.UserFragment

import kotlinx.android.synthetic.main.activity_user.*

class UserActivity : AppCompatActivity() {
    companion object {
        val EXTRA_GITHUB_REPOSITORY_URL = "EXTRA_GITHUB_REPOSITORY_URL"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)

        val reposUrl = intent!!.getStringExtra(EXTRA_GITHUB_REPOSITORY_URL)!!

        val fragment = fragmentGitHubUser as UserFragment

        fragment.reposUrl = reposUrl
    }
}
