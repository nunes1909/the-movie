package com.gabriel.themovie.ui.views.detalhes

import android.view.LayoutInflater
import android.view.ViewGroup
import com.gabriel.themovie.databinding.FragmentDetalhesBinding
import com.gabriel.themovie.util.base.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetalhesFragment : BaseFragment<FragmentDetalhesBinding, DetalhesViewModel>() {

    override val viewModel: DetalhesViewModel by viewModel()

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentDetalhesBinding =
        FragmentDetalhesBinding.inflate(layoutInflater, container, false)
}
