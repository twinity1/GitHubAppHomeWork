package com.example.githubhomework.ui.signin

import android.widget.Toast
import com.example.githubhomework.R
import com.example.githubhomework.tools.ErrorMessageHandler
import com.example.githubhomework.tools.Identity.IdentityManager
import kotlinx.android.synthetic.main.fragment_sign_in.*

class SignInFailureEvent {

    fun failure(owner: SignInFragment, t: Throwable) {
        if (t is IdentityManager.BadCredentials) {
            owner.password.error = t.message
            return
        }

        if (t is SignInViewModel.UsernameIsRequiredException) {
            val error = owner.requireActivity().resources.getString(R.string.username_required)
            owner.username.error = error

            return
        }

        if (t is SignInViewModel.PasswordIsRequiredException) {
            val error = owner.requireActivity().resources.getString(R.string.password_required)
            owner.password.error = error

            return
        }

        Toast.makeText(
            owner.requireContext(),
            ErrorMessageHandler().getStringByException(t, owner.requireActivity().resources),
            Toast.LENGTH_LONG
        ).show()
    }
}