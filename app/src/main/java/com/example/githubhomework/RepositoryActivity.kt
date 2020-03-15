package com.example.githubhomework

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubhomework.components.lists.issues.IssuesListAdapter
import com.example.githubhomework.databinding.ActivityRepositoryBinding
import com.example.githubhomework.databinding.FragmentRepositoryBinding
import com.example.githubhomework.repositories.IssueRepository
import com.example.githubhomework.tools.ErrorMessageHandler
import com.example.githubhomework.ui.repository.RepositoryFragment
import com.example.githubhomework.ui.repository.RepositoryViewModel
import com.example.githubhomework.ui.repository.findBehavior
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_repository.*
import ru.semper_viventem.backdrop.BackdropBehavior

class RepositoryActivity : AppCompatActivity() {
    companion object {
        val REPOSITORY_FULLNAME_URL = "repository_full_name"
    }

    private lateinit var viewModel: RepositoryViewModel

    private lateinit var binding: ActivityRepositoryBinding

    private lateinit var backdropBehavior: BackdropBehavior

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_repository)

        (fragmentRepository as RepositoryFragment).repositoryFullName = intent.getStringExtra(REPOSITORY_FULLNAME_URL)!!

    }
}
