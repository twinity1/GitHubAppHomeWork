package com.example.githubhomework.ui.repository

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubhomework.IssueActivity

import com.example.githubhomework.R
import com.example.githubhomework.components.lists.issues.IssuesListAdapter
import com.example.githubhomework.databinding.FragmentRepositoryBinding
import com.example.githubhomework.repositories.IssueRepository
import com.example.githubhomework.tools.ErrorMessageHandler
import kotlinx.android.synthetic.main.activity_repository.*
import kotlinx.android.synthetic.main.fragment_repository.*
import ru.semper_viventem.backdrop.BackdropBehavior

class RepositoryFragment : Fragment() {
    private lateinit var viewModel: RepositoryViewModel

    private lateinit var binding: FragmentRepositoryBinding

    lateinit var repositoryFullName: String

    private lateinit var backdropBehavior: BackdropBehavior

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProviders.of(this).get(RepositoryViewModel::class.java)

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_repository, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        backdropBehavior = foregroundContainer.findBehavior()

        with(backdropBehavior) {
            attachBackLayout(R.id.backLayout)
        }

        navigationView.setNavigationItemSelectedListener { item ->
            backdropBehavior.close()
            true
        }
    }

    override fun onStart() {
        super.onStart()

       IssueRepository.shared.findAll(repositoryFullName) {
           it.fold(
               onSuccess = {
                   val adapter = IssuesListAdapter(it)

                   adapter.onIssueShow = {
                       val intent = Intent(activity, IssueActivity::class.java)

                       intent.putExtra(IssueActivity.ISSUE_URL, it.entity.url)

                       startActivity(intent)
                   }

                   repositoryIssueList.layoutManager = LinearLayoutManager(activity)
                   repositoryIssueList.adapter = adapter
               },
               onFailure = {
                    Toast.makeText(activity!!, ErrorMessageHandler().getStringByException(it, activity!!.resources), Toast.LENGTH_LONG).show()

                   activity?.finish()
               }
           )
       }
    }
}

fun <T : CoordinatorLayout.Behavior<*>> View.findBehavior(): T = layoutParams.run {
    if (this !is CoordinatorLayout.LayoutParams) throw IllegalArgumentException("View's layout params should be CoordinatorLayout.LayoutParams")

    (layoutParams as CoordinatorLayout.LayoutParams).behavior as? T
        ?: throw IllegalArgumentException("Layout's behavior is not current behavior")
}
