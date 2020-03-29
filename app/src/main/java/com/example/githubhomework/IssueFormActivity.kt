package com.example.githubhomework

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.githubhomework.ui.issueform.IssueFormFragment

class IssueFormActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.issue_form_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, IssueFormFragment.newInstance())
                .commitNow()
        }
    }
}
