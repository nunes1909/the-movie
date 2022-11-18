package com.gabriel.themovie.ui.view.login

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.lifecycleScope
import com.gabriel.themovie.ui.view.login.validaLogin.ValidaLogin
import com.gabriel.domain.util.state.ResourceState
import com.gabriel.themovie.databinding.FragmentLoginBinding
import com.gabriel.themovie.usuario.model.UsuarioView
import com.gabriel.themovie.util.base.BaseFragmentOut
import com.gabriel.themovie.util.constants.ConstantsView.TOKEN_ID_CLIENT
import com.gabriel.themovie.util.extensions.toast
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginFragment : BaseFragmentOut<FragmentLoginBinding, LoginViewModel>() {

    override val viewModel: LoginViewModel by viewModel()
    private lateinit var googleSignInClient: GoogleSignInClient

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configuraGoogle()
        goLogin()
        goLoginGoogle()
        goCadastro()
        observerAuth()
        observerAuthGoogle()
    }

    private fun goLogin() {
        binding.btnLoginLogar.setOnClickListener {
            clearErrors()
            validaCampos()
        }
    }

    private fun goLoginGoogle() {
        binding.btnLoginGoogle.setOnClickListener {
            signInGoogle()
        }
    }

    private fun clearErrors() = with(binding) {
        etLoginEmail.error = null
        etLoginSenha.error = null
    }

    private fun validaCampos() {
        val email = binding.etLoginEmail.text?.trim().toString()
        val senha = binding.etLoginSenha.text?.trim().toString()

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
        viewModel.auth.collect { resource ->
            when (resource) {
                is ResourceState.Default -> {
                    defineAcaoPosAuth(resource)
                }
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

    private fun configuraGoogle() {
        val googleSign = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(TOKEN_ID_CLIENT)
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(requireContext(), googleSign)
    }

    private fun signInGoogle() {
        val signInIntent = googleSignInClient.signInIntent
        resultLauncher.launch(signInIntent)
    }

    private var resultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
                viewModel.autenticaUsuarioGoogle(task)
            }
        }

    private fun observerAuthGoogle() = lifecycleScope.launch {
        viewModel.authGoogle.collect { resource ->
            when (resource) {
                is ResourceState.Default -> {
                    defineAcaoPosAuth(resource)
                }
                else -> {}
            }
        }
    }

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentLoginBinding =
        FragmentLoginBinding.inflate(inflater, container, false)
}
