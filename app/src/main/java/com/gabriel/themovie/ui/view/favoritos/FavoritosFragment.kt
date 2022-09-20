package com.gabriel.themovie.ui.view.favoritos

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.gabriel.domain.util.state.ResourceState
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
        configuraPesquisa()
        observerListaFav()
    }

    private fun configuraPesquisa() = with(binding) {
        editPesquisa.addTextChangedListener(searchMoviesWatcher())
    }

    private fun searchMoviesWatcher() = object : TextWatcher {
        override fun onTextChanged(query: CharSequence, p1: Int, p2: Int, p3: Int) {
            if (query.isNotEmpty()) {
                viewModel.getAllFav(query = query.toString())
            } else {
                viewModel.getAllFav()
            }
        }

        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            // Sem implementação
        }

        override fun afterTextChanged(p0: Editable?) {
            // Sem implementação
        }
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
                    binding.layoutEditPesquisa.show()
                    binding.progressFavoritos.hide()
                    binding.includeLayoutEmpty.imageEmpty.hide()
                    binding.includeLayoutEmpty.textViewEmpty.hide()
                }
                is ResourceState.Error -> {
                    binding.progressFavoritos.hide()
                    binding.layoutEditPesquisa.hide()
                    binding.includeLayoutEmpty.imageEmpty.show()
                    binding.includeLayoutEmpty.textViewEmpty.show()
                    Timber.tag("FilmesFragment/observerListaFilmes")
                        .e("Error -> ${resource.message} Cod -> ${resource.cod}")
                }
                is ResourceState.Loading -> {
                    binding.progressFavoritos.show()
                    binding.editPesquisa.isEnabled = false
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

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentFavoritosBinding =
        FragmentFavoritosBinding.inflate(layoutInflater, container, false)
}
