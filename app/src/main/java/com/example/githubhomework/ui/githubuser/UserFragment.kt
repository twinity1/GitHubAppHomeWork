package com.example.githubhomework.ui.githubuser

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubhomework.R
import com.example.githubhomework.RepositoryActivity
import com.example.githubhomework.components.lists.repositories.RepositoryListAdapter
import com.example.githubhomework.databinding.FragmentUserBinding
import com.example.githubhomework.persistence.repositories.RepositoryRepository
import com.example.githubhomework.tools.ErrorMessageHandler
import com.example.githubhomework.tools.ui.addDivider
import kotlinx.android.synthetic.main.fragment_user.*
import org.koin.android.ext.android.inject

class UserFragment : Fragment() {

    private lateinit var viewModel: UserViewModel

    private lateinit var binding: FragmentUserBinding

    lateinit var reposUrl: String

    private val repositoryRepository: RepositoryRepository by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_user, container, false)

        binding.viewModel = viewModel

        viewModel.repositoryList.observe(viewLifecycleOwner, Observer {
            val repositoryListAdapter = RepositoryListAdapter(it)

            gitHubUserList.layoutManager = LinearLayoutManager(activity)
            gitHubUserList.adapter = repositoryListAdapter

            repositoryListAdapter.onShowRepository = {
                val intent = Intent(activity, RepositoryActivity::class.java)

                intent.putExtra(RepositoryActivity.REPOSITORY_FULLNAME, it.entity.fullName)

                startActivity(intent)
            }
        })

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        gitHubUserList.addDivider(requireContext())
    }

    override fun onStart() {
        super.onStart()

        repositoryRepository.findAllByReposUrl(reposUrl) {
            it.fold(
                onSuccess = {
                    viewModel.repositoryList.value = it

                },
                onFailure = {
                    Toast.makeText(activity, ErrorMessageHandler().getStringByException(it, requireActivity().resources), Toast.LENGTH_LONG).show()
                }
            )
        }

    }
}
