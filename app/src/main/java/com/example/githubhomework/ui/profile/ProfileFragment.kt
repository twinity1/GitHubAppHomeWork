package com.example.githubhomework.ui.profile

import android.os.Bundle
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

    companion object {
        val LAST_SCREEN = "last_screen"
    }

    private enum class Screen {
        none,
        profileContent,
        signIn
    }

    private var lastScreen = Screen.none

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        redraw()

        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    private fun getNextScreen(): Screen
    {
        if (identityManager.identity == null) {
            return Screen.signIn
        }

        return Screen.profileContent
    }

    private fun redraw() {
        val manager = requireActivity().supportFragmentManager

        if (getNextScreen() == Screen.signIn) {
            val fragment = manager.fragmentFactory.instantiate(ClassLoader.getSystemClassLoader(), SignInFragment::class.getFullName()) as SignInFragment

            fragment.onSuccessSignIn = {
                redraw()
            }

            manager.beginTransaction().replace(R.id.fragmentContainer, fragment).commit()
        } else if(getNextScreen() == Screen.profileContent) {
            val fragment = manager.fragmentFactory.instantiate(ClassLoader.getSystemClassLoader(), ProfileContentFragment::class.getFullName()) as ProfileContentFragment

            fragment.onSignOut = {
                redraw()
            }

            manager.beginTransaction().replace(R.id.fragmentContainer, fragment).commit()
        }

        lastScreen = getNextScreen()
    }
}
