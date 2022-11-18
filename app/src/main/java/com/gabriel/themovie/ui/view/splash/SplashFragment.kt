package com.gabriel.themovie.ui.view.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.lifecycle.lifecycleScope
import com.gabriel.themovie.NavGraphDirections
import com.gabriel.themovie.databinding.FragmentSplashBinding
import com.gabriel.themovie.ui.view.login.LoginViewModel
import com.gabriel.themovie.util.base.BaseFragmentOut
import com.gabriel.themovie.util.constants.ConstantsView.KEY_BOTTOM_NAV
import com.gabriel.themovie.util.preferences.dataStore
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class SplashFragment : BaseFragmentOut<FragmentSplashBinding, LoginViewModel>() {

    override val viewModel: LoginViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configuraLogoSplash()
        configuraVisibilityBottomNav()
        firebaseAuth.signOut()
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
        imageSplash.animate().setDuration(2000).alpha(1f).withEndAction { verificaEstaLogado() }
    }

    private fun verificaEstaLogado() {
        if (firebaseAuth.currentUser != null) {
            val actionFilmes = NavGraphDirections.acaoGlobaParaFilmes()
            controller.navigate(actionFilmes)
        } else {
            val actionLogin = SplashFragmentDirections.acaoGlobalParaLogin()
            controller.navigate(actionLogin)
        }
    }

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentSplashBinding =
        FragmentSplashBinding.inflate(inflater, container, false)
}
