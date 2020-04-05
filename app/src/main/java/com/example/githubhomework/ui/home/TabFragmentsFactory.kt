package com.example.githubhomework.ui.home

import com.example.githubhomework.R
import com.example.githubhomework.persistence.repositories.RepositoryRepository
import com.example.githubhomework.tools.Identity.IdentityManager
import com.example.githubhomework.ui.recentrepositories.RecentRepositoriesFragment
import org.koin.ext.getFullName

class TabFragmentsFactory(private val repositoryRepository: RepositoryRepository, private val identityManager: IdentityManager) {
    fun getFragments(owner: HomeFragment): List<RecentRepositoriesFragment>
    {
        val fragments = ArrayList<RecentRepositoriesFragment>()

        val notMyRepositories = getUnownedReposFragment(owner)
        fragments.add(notMyRepositories)

        if (identityManager.identity != null) {
            val myRepositories = getOwnedReposFragment(owner)
            fragments.add(myRepositories)
        }

        return fragments
    }

    private fun getUnownedReposFragment(owner: HomeFragment): RecentRepositoriesFragment {
        val fragment =
            owner.requireActivity().supportFragmentManager.fragmentFactory.instantiate(
                ClassLoader.getSystemClassLoader(),
                RecentRepositoriesFragment::class.getFullName()
            ) as RecentRepositoriesFragment

        fragment.title = owner.resources.getString(R.string.unowned_recent_repos)

        repositoryRepository.findAllUnownedRecentVisited {
            fragment.viewModel.repositories.value = it
        }

        return fragment
    }

    private fun getOwnedReposFragment(owner: HomeFragment): RecentRepositoriesFragment {
        val fragment =
            owner.requireActivity().supportFragmentManager.fragmentFactory.instantiate(
                ClassLoader.getSystemClassLoader(),
                RecentRepositoriesFragment::class.getFullName()
            ) as RecentRepositoriesFragment

        fragment.title = owner.resources.getString(R.string.my_recent_repos)

        repositoryRepository.findAllOwnedRecentVisited {
            fragment.viewModel.repositories.value = it
        }

        return fragment
    }
}