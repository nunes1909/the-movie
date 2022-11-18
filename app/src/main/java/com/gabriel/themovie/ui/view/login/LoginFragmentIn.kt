package com.gabriel.themovie.ui.view.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gabriel.themovie.databinding.FragmentLoginBinding
import com.gabriel.themovie.util.base.BaseFragmentOut
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginFragmentIn : BaseFragmentOut<FragmentLoginBinding, LoginViewModel>() {

    override val viewModel: LoginViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        goCadastro()
    }

    private fun goCadastro() {
        binding.btnLoginCadastrar.setOnClickListener {
            val action = LoginFragmentInDirections.acaoLoginParaCadastro()
            controller.navigate(action)
        }
    }

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentLoginBinding =
        FragmentLoginBinding.inflate(inflater, container, false)
}
