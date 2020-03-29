package com.example.githubhomework.ui.repository

import android.opengl.Visibility
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.example.githubhomework.R
import com.example.githubhomework.components.ui.backdrop.findBehavior
import com.example.githubhomework.databinding.FragmentRepositoryBinding
import com.example.githubhomework.repositories.IssueRepository
import com.example.githubhomework.tools.ErrorMessageHandler
import com.example.githubhomework.tools.Identity.IdentityManager
import kotlinx.android.synthetic.main.fragment_repository.*
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
        viewModel = ViewModelProviders.of(this).get(RepositoryViewModel::class.java)

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_repository, container, false)

        binding.lifecycleOwner = this

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        backdropBehavior = foregroundContainer.findBehavior()

        with(backdropBehavior) {
            attachBackLayout(R.id.backLayout)
            backdropBehavior.setClosedIcon(R.drawable.baseline_filter_list_white_24)
        }

        viewModel.labelList.observe(viewLifecycleOwner, labelObserver.create(this))
        viewModel.issueList.observe(viewLifecycleOwner, issueObserver.create(this))
    }

    override fun onStart() {
        super.onStart()

        toolbar.title = repositoryFullName

        val isOwner = identityManager.identity?.isOwnerOf(repositoryFullName) ?: false
        if (!isOwner) {
            addIssue.visibility = View.GONE;
        }

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
