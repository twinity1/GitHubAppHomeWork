package com.example.githubhomework.ui.profilecontent

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil

import com.example.githubhomework.R
import com.example.githubhomework.databinding.FragmentProfileBinding
import com.example.githubhomework.databinding.FragmentProfileContentBinding
import com.example.githubhomework.tools.Identity.IdentityManager
import org.koin.android.ext.android.inject

class ProfileContentFragment : Fragment() {
    private lateinit var binding: FragmentProfileContentBinding

    private val viewModel: ProfileContentViewModel by inject()
    private val identityManager: IdentityManager by inject()

    var onSignOut = {}

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_profile_content, container, false)

        viewModel.username.value = identityManager.identity?.username

        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        viewModel.onSignOut = {
            onSignOut()
        }

        return binding.root
    }
}
