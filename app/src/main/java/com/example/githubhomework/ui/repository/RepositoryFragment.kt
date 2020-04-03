package com.example.githubhomework.ui.repository

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.githubhomework.IssueFormActivity
import com.example.githubhomework.R
import com.example.githubhomework.components.ui.backdrop.findBehavior
import com.example.githubhomework.databinding.FragmentRepositoryBinding
import com.example.githubhomework.persistence.repositories.IssueRepository
import com.example.githubhomework.tools.ErrorMessageHandler
import com.example.githubhomework.tools.Identity.IdentityManager
import com.example.githubhomework.tools.ui.addDivider
import kotlinx.android.synthetic.main.fragment_repository.*
import kotlinx.android.synthetic.main.fragment_search_user.*
import ru.semper_viventem.backdrop.BackdropBehavior
import org.koin.android.ext.android.inject

class RepositoryFragment : Fragment() {
    lateinit var viewModel: RepositoryViewModel

    private lateinit var binding: FragmentRepositoryBinding

    lateinit var repositoryFullName: String

    private lateinit var backdropBehavior: BackdropBehavior

    private val issueRepository: IssueRepository by inject()
    private val labelObserver: LabelObserver by inject()
    private val issueObserver: IssueObserver by inject()
    private val identityManager: IdentityManager by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this).get(RepositoryViewModel::class.java)

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_repository, container, false)

        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        repositoryIssueList.addDivider(requireContext())

        backdropBehavior = foregroundContainer.findBehavior()

        with(backdropBehavior) {
            attachBackLayout(R.id.backLayout)
            backdropBehavior.setClosedIcon(R.drawable.baseline_filter_list_white_24)
        }

        viewModel.labelList.observe(viewLifecycleOwner, labelObserver.create(this))
        viewModel.issueList.observe(viewLifecycleOwner, issueObserver.create(this))

        viewModel.onNewIssueShow = {
            val intent = Intent(requireActivity(), IssueFormActivity::class.java)
            intent.putExtra(IssueFormActivity.FULL_REPOSITORY_NAME, repositoryFullName)
            startActivityForResult(intent, IssueFormActivity.SUCCESS)
        }
    }

    override fun onStart() {
        super.onStart()

        toolbar.title = repositoryFullName

        refresh()
    }

    fun refresh() {
        issueRepository.findAll(repositoryFullName) {
            it.fold(
                onSuccess = {
                    viewModel.issueData = it
                },
                onFailure = {
                    Toast.makeText(requireActivity(), ErrorMessageHandler().getStringByException(it, requireActivity().resources), Toast.LENGTH_LONG).show()

                    activity?.finish()
                }
            )
        }
    }
}
