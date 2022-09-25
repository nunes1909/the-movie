package com.gabriel.themovie.ui.view.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.gabriel.themovie.databinding.FragmentSplashBinding
import com.gabriel.themovie.util.constants.ConstantsView.KEY_BOTTOM_NAV
import com.gabriel.themovie.util.preferences.dataStore
import kotlinx.coroutines.launch

class SplashFragment : Fragment() {

    private val binding by lazy { FragmentSplashBinding.inflate(layoutInflater) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configuraLogoSplash()
        configuraVisibilityBottomNav()
    }

    private fun configuraVisibilityBottomNav() {
        lifecycleScope.launch {
            requireContext().dataStore.edit { preferences ->
                preferences[booleanPreferencesKey(KEY_BOTTOM_NAV)] = false
            }
        }
    }

    private fun configuraLogoSplash() = with(binding) {
        imageSplash.alpha = 0f
        imageSplash.animate().setDuration(2000).alpha(1f).withEndAction { goToFilmes() }
    }

    private fun goToFilmes() {
        val action = SplashFragmentDirections
            .acaoSplashParaFilmes()
        findNavController().navigate(action)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = binding.root
}