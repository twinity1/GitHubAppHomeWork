package com.example.githubhomework

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.iterator
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.githubhomework.ui.aboutapp.AboutAppFragment
import com.example.githubhomework.ui.home.HomeFragment
import com.example.githubhomework.ui.profile.ProfileFragment
import com.google.android.material.bottomnavigation.BottomNavigationMenu
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_repository.*
import org.koin.ext.getFullName

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        val fragments = listOf(
            supportFragmentManager.fragmentFactory.instantiate(ClassLoader.getSystemClassLoader(), HomeFragment::class.getFullName()),
            supportFragmentManager.fragmentFactory.instantiate(ClassLoader.getSystemClassLoader(), ProfileFragment::class.getFullName()),
            supportFragmentManager.fragmentFactory.instantiate(ClassLoader.getSystemClassLoader(), AboutAppFragment::class.getFullName())
        )

        val trans = supportFragmentManager.beginTransaction()

        fragments.forEach {
                trans
                    .add(R.id.nav_host_fragment, it)
                    .hide(it)
        }

        trans.commit()

        navView.setOnNavigationItemSelectedListener {
            val trans = supportFragmentManager.beginTransaction()

            val items = nav_view.menu.iterator().asSequence().toList()

            supportActionBar?.title = it.title

            val newFragment = fragments[items.indexOf(it)]

            fragments.forEach {
                if (it != newFragment) {
                    trans.hide(it)
                }
            }

            trans.show(newFragment).commit()

            true
        }

        navView.selectedItemId = nav_view.menu.iterator().asSequence().toList().first().itemId
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.home_menu, menu)

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.action_search -> {
            val intent = Intent(this, SearchUserActivity::class.java)
            startActivity(intent)
            true
        }
        else -> {
            super.onOptionsItemSelected(item)
        }
    }
}
