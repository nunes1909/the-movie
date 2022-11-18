package com.gabriel.themovie.ui.view.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.gabriel.themovie.ui.view.login.validaLogin.ValidaLogin
import com.gabriel.domain.util.state.ResourceState
import com.gabriel.themovie.databinding.FragmentLoginBinding
import com.gabriel.themovie.usuario.model.UsuarioView
import com.gabriel.themovie.util.base.BaseFragmentOut
import com.gabriel.themovie.util.extensions.toast
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginFragment : BaseFragmentOut<FragmentLoginBinding, LoginViewModel>() {

    override val viewModel: LoginViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        goLogin()
        goCadastro()
        observerAuth()
    }

    private fun goLogin() {
        binding.btnLoginLogar.setOnClickListener {
            clearErrors()
            validaCampos()
        }
    }

    private fun clearErrors() = with(binding) {
        etLoginEmail.error = null
        etLoginSenha.error = null
    }

    private fun validaCampos() = with(binding) {
        val email = etLoginEmail.text?.trim().toString()
        val senha = etLoginSenha.text?.trim().toString()

        val resource = ValidaLogin().validaCamposFormulario(email = email, senha = senha)

        when (resource) {
            is ResourceState.Success -> {
                viewModel.autenticaUsuario(UsuarioView(email = email, senha = senha))
            }
            else -> {
                toast(resource.message!!)
            }
        }
    }

    private fun observerAuth() = lifecycleScope.launch {
        viewModel.autentica.collect { resource ->
            when (resource) {
                is ResourceState.Default -> { defineAcaoPosAuth(resource) }
                else -> {}
            }
        }
    }

    private fun defineAcaoPosAuth(resource: ResourceState<Boolean>) {
        if (resource.data!!) {
            val action = LoginFragmentDirections.acaoGlobaParaFilmes()
            controller.navigate(action)
        } else {
            toast(resource.message!!)
        }
    }

    private fun goCadastro() {
        binding.btnLoginCadastrar.setOnClickListener {
            val action = LoginFragmentDirections.acaoLoginParaCadastro()
            controller.navigate(action)
        }
    }

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentLoginBinding =
        FragmentLoginBinding.inflate(inflater, container, false)
}
