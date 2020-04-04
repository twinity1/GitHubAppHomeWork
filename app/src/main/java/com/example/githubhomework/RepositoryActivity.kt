package com.example.githubhomework

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.githubhomework.ui.repository.RepositoryFragment
import kotlinx.android.synthetic.main.activity_repository.*

class RepositoryActivity : AppCompatActivity() {
    companion object {
        val REPOSITORY_FULLNAME = "repository_full_name"
    }

    private lateinit var repositoryFragment: RepositoryFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_repository)

        repositoryFragment = fragmentRepository as RepositoryFragment
        repositoryFragment.repositoryFullName = intent.getStringExtra(REPOSITORY_FULLNAME)!!
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == IssueFormActivity.SUCCESS) {
            repositoryFragment.refresh()
        }
    }
}
