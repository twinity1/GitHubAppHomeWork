package com.example.githubhomework.ui.profile

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.githubhomework.R
import com.example.githubhomework.tools.Identity.IdentityManager
import com.example.githubhomework.ui.profilecontent.ProfileContentFragment
import com.example.githubhomework.ui.signin.SignInFragment
import org.koin.android.ext.android.inject
import org.koin.ext.getFullName

class ProfileFragment : Fragment() {
    private val identityManager: IdentityManager by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        redraw()

        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    private fun redraw() {
        val identity = identityManager.identity
        val manager = requireActivity().supportFragmentManager

        if (identity == null) {
            val fragment = manager.fragmentFactory.instantiate(ClassLoader.getSystemClassLoader(), SignInFragment::class.getFullName()) as SignInFragment

            fragment.onSuccessSignIn = {
                redraw()
            }

            manager.beginTransaction().replace(R.id.fragmentContainer, fragment).commit()
        } else {
            val fragment = manager.fragmentFactory.instantiate(ClassLoader.getSystemClassLoader(), ProfileContentFragment::class.getFullName()) as ProfileContentFragment

            fragment.onSignOut = {
                redraw()
            }

            manager.beginTransaction().replace(R.id.fragmentContainer, fragment).commit()
        }
    }
}
