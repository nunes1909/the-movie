package com.gabriel.themovie.ui.view.conta

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gabriel.themovie.databinding.FragmentContaBinding
import com.gabriel.themovie.ui.view.login.LoginViewModel
import com.gabriel.themovie.util.base.BaseFragmentOut
import org.koin.androidx.viewmodel.ext.android.viewModel

class ContaFragment : BaseFragmentOut<FragmentContaBinding, LoginViewModel>() {

    override val viewModel: LoginViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configuraEmail()
        goFavoritos()
        goLogout()
    }

    private fun configuraEmail() {
        binding.tvContaEmail.text = firebaseAuth.currentUser!!.email
    }

    private fun goFavoritos() {
        binding.btnContaGoFavoritos.setOnClickListener {
            val action = ContaFragmentDirections.acaoContaParaFavoritos()
            controller.navigate(action)
        }
    }

    private fun goLogout() {
        binding.btnContaSair.setOnClickListener {
            firebaseAuth.signOut()
            verificaEstaLogado()
        }
    }

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentContaBinding =
        FragmentContaBinding.inflate(inflater, container, false)
}
