package com.gabriel.themovie.ui.views.series

import android.view.LayoutInflater
import android.view.ViewGroup
import com.gabriel.themovie.databinding.FragmentSeriesBinding
import com.gabriel.themovie.util.base.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class SeriesFragment : BaseFragment<FragmentSeriesBinding, SeriesViewModel>() {

    override val viewModel: SeriesViewModel by viewModel()

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentSeriesBinding =
        FragmentSeriesBinding.inflate(layoutInflater, container, false)
}
