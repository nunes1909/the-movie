package com.gabriel.themovie.ui.views.pesquisa

import android.view.LayoutInflater
import android.view.ViewGroup
import com.gabriel.themovie.databinding.FragmentPesquisaBinding
import com.gabriel.themovie.util.base.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class PesquisaFragment : BaseFragment<FragmentPesquisaBinding, PesquisaViewModel>() {

    override val viewModel: PesquisaViewModel by viewModel()

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentPesquisaBinding =
        FragmentPesquisaBinding.inflate(layoutInflater, container, false)
}
