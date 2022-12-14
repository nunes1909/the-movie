package com.gabriel.themovie.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.gabriel.themovie.databinding.ActivityMainBinding
import com.gabriel.themovie.util.constants.ConstantsView.KEY_BOTTOM_NAV
import com.gabriel.themovie.util.extensions.hide
import com.gabriel.themovie.util.extensions.show
import com.gabriel.themovie.util.preferences.dataStore
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private lateinit var navHostFragment: NavHostFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        inicializaView()
        configuraBottomNav()
    }

    private fun configuraBottomNav() = lifecycleScope.launch {
        dataStore.data.collect { preferences ->
            preferences[booleanPreferencesKey(KEY_BOTTOM_NAV)]?.let {
                if (it) {
                    binding.bottomNavigation.show()
                } else {
                    binding.bottomNavigation.hide()
                }
            }
        }
    }

    private fun inicializaView() {
        navHostFragment = supportFragmentManager
            .findFragmentById(binding.fragmentContainerView.id) as NavHostFragment

        val navController = navHostFragment.navController

        binding.bottomNavigation.apply {
            setupWithNavController(navController)
            setOnNavigationItemReselectedListener { }
        }
    }
}
