package com.gabriel.themovie.view.ui.cadastro

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.gabriel.domain.util.state.ResourceState
import com.gabriel.themovie.databinding.FragmentCadastroBinding
import com.gabriel.themovie.view.ui.cadastro.validaCadastro.ValidaCadastro
import com.gabriel.themovie.usuario.model.UsuarioView
import com.gabriel.themovie.util.base.BaseFragmentOut
import com.gabriel.themovie.util.extensions.toast
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class CadastroFragment : BaseFragmentOut<FragmentCadastroBinding, CadastroViewModel>() {

    override val viewModel: CadastroViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        goCadastrar()
        observerCadastro()
    }

    private fun goCadastrar() {
        binding.btnCadastroCadastrar.setOnClickListener {
            clearErrors()
            validaCampos()
        }
    }

    private fun clearErrors() = with(binding) {
        etCadastroEmail.error = null
        etCadastroSenha.error = null
        etCadastroConfirmaSenha.error = null
    }

    private fun validaCampos() = with(binding) {
        val email = etCadastroEmail.text?.trim().toString()
        val senha = etCadastroSenha.text?.trim().toString()
        val confirmaSenha = etCadastroConfirmaSenha.text?.trim().toString()

        val resource = ValidaCadastro()
            .validaCamposFormulario(email = email, senha = senha, confirmaSenha = confirmaSenha)

        when (resource) {
            is ResourceState.Success -> {
                viewModel.cadastraUsuario(UsuarioView(email = email, senha = senha))
            }
            else -> {
                toast(resource.message!!)
            }
        }
    }

    private fun observerCadastro() = lifecycleScope.launch {
        viewModel.cadastra.collect { resource ->
            when (resource) {
                is ResourceState.Default -> { defineAcaoPosCadastro(resource) }
                else -> {}
            }
        }
    }

    private fun defineAcaoPosCadastro(resource: ResourceState<Boolean>) {
        if (resource.data!!) {
            toast("Cadastro realizado com sucesso.")
            val action = CadastroFragmentDirections.acaoGlobalParaLogin()
            controller.navigate(action)
        } else {
            toast(resource.message!!)
        }
    }

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentCadastroBinding =
        FragmentCadastroBinding.inflate(inflater, container, false)
}
