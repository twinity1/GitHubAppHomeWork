package com.example.githubhomework

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity

import kotlinx.android.synthetic.main.activity_git_hub_user.*

class GitHubUserActivity : AppCompatActivity() {
    companion object {
        val EXTRA_GITHUB_REPOSITORY_URL = "EXTRA_GITHUB_REPOSITORY_URL"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_git_hub_user)
        setSupportActionBar(toolbar)

        if (savedInstanceState == null) {
            return
        }

        val reposUrl = savedInstanceState!!.getString(EXTRA_GITHUB_REPOSITORY_URL)


//        nav:h


    }
}
