package com.example.githubhomework

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.githubhomework.databinding.ActivityRepositoryBinding
import com.example.githubhomework.ui.repository.RepositoryFragment
import com.example.githubhomework.ui.repository.RepositoryViewModel
import kotlinx.android.synthetic.main.activity_repository.*
import ru.semper_viventem.backdrop.BackdropBehavior

class RepositoryActivity : AppCompatActivity() {
    companion object {
        val REPOSITORY_FULLNAME_URL = "repository_full_name"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_repository)

        (fragmentRepository as RepositoryFragment).repositoryFullName = intent.getStringExtra(REPOSITORY_FULLNAME_URL)!!
    }
}
