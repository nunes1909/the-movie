package com.gabriel.themovie.ui.view.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.gabriel.themovie.databinding.FragmentSplashBinding

class SplashFragment : Fragment() {

    private val binding by lazy { FragmentSplashBinding.inflate(layoutInflater) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configuraLogoSplash()
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