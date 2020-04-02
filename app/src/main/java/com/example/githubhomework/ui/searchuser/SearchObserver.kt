package com.example.githubhomework.ui.searchuser

import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.lifecycle.Observer
import com.example.githubhomework.persistence.repositories.UserRepository
import com.example.githubhomework.tools.ErrorMessageHandler
import com.example.githubhomework.ui.searchuser.SearchUserFragment

class SearchObserver(private val userRepository: UserRepository) {

    fun create(owner: SearchUserFragment): Observer<String> {
        val handler = Handler(Looper.getMainLooper())

        return Observer {
            handler.removeCallbacksAndMessages(null)

            if (it.trim() != "") {
                handler.postDelayed({
                    userRepository.findByName(it) {
                        it.fold(
                            onSuccess = {
                                owner.viewModel.searchResult.value = it
                            },

                            onFailure = {
                                Toast.makeText(owner.requireActivity(), ErrorMessageHandler().getStringByException(it, owner.requireActivity().resources), Toast.LENGTH_LONG).show()
                            }
                        )
                    }
                }, 300)
            } else {
                owner.viewModel.searchResult.value = listOf()
            }
        }
    }
}