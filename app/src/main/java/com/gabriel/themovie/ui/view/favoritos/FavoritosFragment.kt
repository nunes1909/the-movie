package com.gabriel.themovie.ui.view.favoritos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.gabriel.domain.util.state.ResourceState
import com.gabriel.themovie.R
import com.gabriel.themovie.databinding.FragmentFavoritosBinding
import com.gabriel.themovie.movie.model.MovieView
import com.gabriel.themovie.ui.adapters.MovieAdapterSecondary
import com.gabriel.themovie.util.base.BaseFragment
import com.gabriel.themovie.util.extensions.hide
import com.gabriel.themovie.util.extensions.show
import com.gabriel.themovie.util.extensions.toast
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

class FavoritosFragment : BaseFragment<FragmentFavoritosBinding, FavoritosViewModel>() {

    override val viewModel: FavoritosViewModel by viewModel()
    private val movieAdapter by lazy { MovieAdapterSecondary() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configuraRecyclerView()
        observerListaFav()
    }

    private fun configuraRecyclerView() = with(binding) {
        rvFavoritos.adapter = movieAdapter
        rvFavoritos.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun observerListaFav() = lifecycleScope.launch {
        viewModel.getFav.collect { resource ->
            when (resource) {
                is ResourceState.Success -> {
                    exibeFavoritos(resource)
                    ocultaProgressBar(binding.progressFavoritos)
                }
                is ResourceState.Error -> {
                    ocultaProgressBar(binding.progressFavoritos)
                    toast(getString(R.string.um_erro_ocorreu))
                    Timber.tag("FilmesFragment/observerListaFilmes")
                        .e("Error -> ${resource.message} Cod -> ${resource.cod}")
                }
                is ResourceState.Loading -> {
                    exibeProgressBar(binding.progressFavoritos)
                }
                else -> {}
            }
        }
    }

    private fun exibeFavoritos(resource: ResourceState.Success<List<MovieView>>) {
        resource.data?.let { results ->
            movieAdapter.moviesList = results
        }
    }

    private fun ocultaProgressBar(progress: View) {
        progress.hide()
    }

    private fun exibeProgressBar(progress: View) {
        progress.show()
    }

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentFavoritosBinding =
        FragmentFavoritosBinding.inflate(layoutInflater, container, false)
}
