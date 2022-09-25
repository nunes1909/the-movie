package com.gabriel.themovie.ui.view.favoritos

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.ItemTouchHelper.LEFT
import androidx.recyclerview.widget.ItemTouchHelper.RIGHT
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
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
        observerEmpty()
        configuraClickAdapter()
    }

    private fun configuraRecyclerView() = with(binding) {
        rvFavoritos.adapter = movieAdapter
        rvFavoritos.layoutManager = LinearLayoutManager(requireContext())
        ItemTouchHelper(configuraTouchHelper()).attachToRecyclerView(rvFavoritos)
    }

    private fun configuraTouchHelper(): ItemTouchHelper.SimpleCallback {
        return object : ItemTouchHelper.SimpleCallback(0, LEFT or RIGHT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ) = false

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val movieView = movieAdapter.moviesList[viewHolder.adapterPosition]
                viewModel.deleteMovie(movieView = movieView).also {
                    toast("${movieView.title} removido.")
                    viewModel.getAllFav()
                }
            }
        }
    }

    private fun configuraPesquisa() = with(binding) {
        etPesquisa.addTextChangedListener(searchMoviesWatcher())
        etPesquisa.requestFocus()
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

    private fun observerListaFav() = lifecycleScope.launch {
        viewModel.getFav.collect { resource ->
            when (resource) {
                is ResourceState.Success -> {
                    exibeFavoritos(resource)
                    binding.pbFavoritos.hide()
                }
                is ResourceState.Error -> {
                    binding.pbFavoritos.hide()
                    Timber.tag("FilmesFragment/observerListaFilmes")
                        .e("Error -> ${resource.message} Cod -> ${resource.cod}")
                }
                is ResourceState.Loading -> {
                    binding.pbFavoritos.show()
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

    private fun observerEmpty() = lifecycleScope.launch {
        viewModel.empty.collect {
            if (!it) {
                movieAdapter.moviesList = listOf()
                binding.containerIncludeEmpty.ivEmpty.show()
                binding.containerIncludeEmpty.tvEmpty.show()
            } else {
                binding.containerIncludeEmpty.ivEmpty.hide()
                binding.containerIncludeEmpty.tvEmpty.hide()
            }
        }
    }

    private fun configuraClickAdapter() {
        movieAdapter.setMovieOnClickListener { movieView ->
            val action = FavoritosFragmentDirections
                .acaoFavoritosParaDetalhes(movieView = movieView)
            findNavController().navigate(action)
        }
    }

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentFavoritosBinding =
        FragmentFavoritosBinding.inflate(layoutInflater, container, false)
}
