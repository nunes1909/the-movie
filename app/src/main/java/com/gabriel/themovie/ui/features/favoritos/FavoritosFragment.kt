package com.gabriel.themovie.ui.features.favoritos

import android.view.LayoutInflater
import android.view.ViewGroup
import com.gabriel.themovie.databinding.FragmentFavoritosBinding
import com.gabriel.themovie.ui.features.favoritos.FavoritosViewModel
import com.gabriel.themovie.util.base.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavoritosFragment : BaseFragment<FragmentFavoritosBinding, FavoritosViewModel>() {

    override val viewModel: FavoritosViewModel by viewModel()

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentFavoritosBinding =
        FragmentFavoritosBinding.inflate(layoutInflater, container, false)
}