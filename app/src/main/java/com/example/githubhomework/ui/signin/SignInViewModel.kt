package com.example.githubhomework.ui.signin

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.githubhomework.persistence.entities.Identity
import com.example.githubhomework.tools.Identity.IdentityManager
import java.lang.Exception

class SignInViewModel(private val identityManager: IdentityManager) : ViewModel() {
    val username = MutableLiveData<String>()
    val password = MutableLiveData<String>()

    var onSuccessSignIn: ((Identity) -> Unit) = {}
    var onFailureSignIn: ((Throwable) -> Unit) = {}

    class UsernameIsRequiredException : Exception()
    class PasswordIsRequiredException : Exception()
    fun onSubmit() {
        if (username.value == null) {
            onFailureSignIn(UsernameIsRequiredException())
            return
        }

        if (password.value == null) {
            onFailureSignIn(PasswordIsRequiredException())
            return
        }

        identityManager.signIn(username.value!!, password.value!!) {
            it.fold(
                onSuccess = {
                    onSuccessSignIn(it)
                },
                onFailure = {
                    onFailureSignIn(it)
                }
            )
        }
    }
}