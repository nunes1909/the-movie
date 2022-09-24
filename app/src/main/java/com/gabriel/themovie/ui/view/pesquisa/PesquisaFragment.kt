package com.gabriel.themovie.ui.view.pesquisa

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.gabriel.domain.util.state.ResourceState
import com.gabriel.themovie.R
import com.gabriel.themovie.databinding.FragmentPesquisaBinding
import com.gabriel.themovie.movie.model.MovieView
import com.gabriel.themovie.ui.adapters.MovieAdapterSecondary
import com.gabriel.themovie.util.base.BaseFragment
import com.gabriel.themovie.util.constants.ConstantsView.TYPE_FILME
import com.gabriel.themovie.util.extensions.hide
import com.gabriel.themovie.util.extensions.show
import com.gabriel.themovie.util.extensions.toast
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

class PesquisaFragment : BaseFragment<FragmentPesquisaBinding, PesquisaViewModel>() {

    override val viewModel: PesquisaViewModel by viewModel()
    private val movieAdapter by lazy { MovieAdapterSecondary() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configuraRecyclerView()
        configuraPesquisa()
        observerSearchList()
        configuraClickAdapter()
    }

    private fun configuraClickAdapter() {
        movieAdapter.setMovieOnClickListener { movieView ->
            movieView.type = TYPE_FILME
            val action = PesquisaFragmentDirections
                .acaoPesquisaParaDetalhes(movieView = movieView)
            findNavController().navigate(action)
        }
    }

    private fun configuraRecyclerView() = with(binding) {
        rvPesquisa.adapter = movieAdapter
        rvPesquisa.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun configuraPesquisa() = with(binding) {
        editPesquisa.addTextChangedListener(searchMoviesWatcher())
    }

    private fun searchMoviesWatcher() = object : TextWatcher {
        override fun onTextChanged(query: CharSequence, p1: Int, p2: Int, p3: Int) {
            if (query.isNotEmpty()) {
                viewModel.searchMovie(query = query.toString())
                binding.rvPesquisa.show()
            } else {
                binding.rvPesquisa.hide()
            }
        }

        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            // Sem implementação
        }

        override fun afterTextChanged(p0: Editable?) {
            // Sem implementação
        }
    }

    private fun observerSearchList() = lifecycleScope.launch {
        viewModel.search.collect { resource ->
            when (resource) {
                is ResourceState.Success -> {
                    exibeSearchList(resource)
                    ocultaProgressBar(binding.progressPesquisa)
                }
                is ResourceState.Error -> {
                    ocultaProgressBar(binding.progressPesquisa)
                    toast(getString(R.string.um_erro_ocorreu))
                    Timber.tag("PesquisaFragment/observerSearchList")
                        .e("Error -> ${resource.message} Cod -> ${resource.cod}")
                }
                is ResourceState.Loading -> {
                    exibeProgressBar(binding.progressPesquisa)
                }
                else -> {}
            }
        }
    }

    private fun exibeSearchList(resource: ResourceState.Success<List<MovieView>>) {
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

    /**
     * Inicializando um objeto global para utilizar ao abrir os detalhes do movie principal.
     * Isso pois eu não tenho acesso ao [objeto] ao clicar no movie principal.
     *
     * @param globalMovie é o objeto global.
     * @param movieView é o objeto inicializado.
     */
//    private fun inicializaGlobalMultiMovie(movieView: MovieView) {
//        globalMovie = movieView
//    }

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentPesquisaBinding =
        FragmentPesquisaBinding.inflate(layoutInflater, container, false)
}
