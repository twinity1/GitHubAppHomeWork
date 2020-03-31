package com.example.githubhomework.ui.signin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.githubhomework.R
import com.example.githubhomework.databinding.FragmentSignInBinding
import com.example.githubhomework.persistence.entities.Identity
import kotlinx.android.synthetic.main.fragment_sign_in.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class SignInFragment : Fragment() {

    val signInViewModel: SignInViewModel by viewModel()

    private lateinit var binding: FragmentSignInBinding

    var onSuccessSignIn: ((Identity) -> Unit) = {}

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_sign_in, container, false)
        binding.viewModel = signInViewModel
        binding.lifecycleOwner = this

        attachActions()

        return binding.root
    }

    private fun attachActions() {
        signInViewModel.password.observe(viewLifecycleOwner, Observer {
            password.error = null
            username.error = null
        })

        signInViewModel.onSuccessSignIn = {
            onSuccessSignIn(it)
        }

        signInViewModel.onFailureSignIn = {
            SignInFailureEvent().failure(this, it)
        }
    }
}