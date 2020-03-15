package com.example.githubhomework

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.githubhomework.ui.repository.RepositoryFragment
import kotlinx.android.synthetic.main.activity_repository.*

class RepositoryActivity : AppCompatActivity() {
    companion object {
        val REPOSITORY_FULLNAME_URL = "repository_full_name"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repository)

        val issuesUrl = intent.getStringExtra(REPOSITORY_FULLNAME_URL)!!

        val fragment = fragmentRepository as RepositoryFragment

        fragment.repositoryFullName = issuesUrl
    }
}
