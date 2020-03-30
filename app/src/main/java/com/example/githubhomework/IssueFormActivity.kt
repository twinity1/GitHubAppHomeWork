package com.example.githubhomework

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.githubhomework.ui.issueform.IssueFormFragment
import kotlinx.android.synthetic.main.issue_form_activity.*

class IssueFormActivity : AppCompatActivity() {
    companion object {
        val FULL_REPOSITORY_NAME = "full_repository_name"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.issue_form_activity)
    }

    override fun onStart() {
        super.onStart()

        val repoName = intent.getStringExtra(FULL_REPOSITORY_NAME)!!

        (issueFragment as IssueFormFragment).repositoryFullName = repoName
    }
}
