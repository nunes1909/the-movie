package com.gabriel.themovie.ui.view.cadastro

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gabriel.themovie.databinding.FragmentCadastroBinding
import com.gabriel.themovie.util.base.BaseFragmentOut
import org.koin.androidx.viewmodel.ext.android.viewModel

class CadastroFragmentIn : BaseFragmentOut<FragmentCadastroBinding, CadastroViewModel>() {

    override val viewModel: CadastroViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentCadastroBinding =
        FragmentCadastroBinding.inflate(inflater, container, false)
}
