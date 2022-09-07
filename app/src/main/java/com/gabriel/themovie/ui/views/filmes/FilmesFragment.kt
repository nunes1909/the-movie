package com.gabriel.themovie.ui.views.filmes

import android.view.LayoutInflater
import android.view.ViewGroup
import com.gabriel.themovie.databinding.FragmentFilmesBinding
import com.gabriel.themovie.util.base.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class FilmesFragment : BaseFragment<FragmentFilmesBinding, FilmesViewModel>() {

    override val viewModel: FilmesViewModel by viewModel()

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentFilmesBinding =
        FragmentFilmesBinding.inflate(layoutInflater, container, false)
}
