package com.gabriel.themovie.ui.views.series

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.gabriel.domain.util.state.ResourceState
import com.gabriel.themovie.databinding.FragmentSeriesBinding
import com.gabriel.themovie.ui.adapters.SerieAdapter
import com.gabriel.themovie.util.base.BaseFragment
import com.gabriel.themovie.util.extensions.hide
import com.gabriel.themovie.util.extensions.show
import com.gabriel.themovie.util.extensions.toast
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

class SeriesFragment : BaseFragment<FragmentSeriesBinding, SeriesViewModel>() {

    override val viewModel: SeriesViewModel by viewModel()
    private val serieAdapter by lazy { SerieAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configuraRecyclerView()
        observerListaSeries()
    }

    private fun observerListaSeries() = lifecycleScope.launch {
        viewModel.list.collect { resources ->
            when (resources) {
                is ResourceState.Success -> {
                    resources.data?.let { results ->
                        binding.progressSerie.hide()
                        serieAdapter.seriesList = results
                    }
                }
                is ResourceState.Error -> {
                    binding.progressSerie.hide()
                    toast("Um erro ocorreu: ${resources.message}")
                    Timber.tag("SeriesFragment/observerListaSeries")
                        .e("Error -> ${resources.message} Cod -> ${resources.cod}")
                }
                is ResourceState.Loading -> {
                    binding.progressSerie.show()
                }
                else -> {}
            }
        }
    }

    private fun configuraRecyclerView() = with(binding) {
        rvSerie.adapter = serieAdapter
        rvSerie.layoutManager = GridLayoutManager(requireContext(), 4)
    }

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentSeriesBinding =
        FragmentSeriesBinding.inflate(layoutInflater, container, false)
}
