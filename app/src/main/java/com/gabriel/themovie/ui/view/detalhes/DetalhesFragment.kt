package com.gabriel.themovie.ui.view.detalhes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import coil.load
import com.gabriel.domain.util.state.ResourceState
import com.gabriel.themovie.databinding.FragmentDetalhesBinding
import com.gabriel.themovie.movie.model.MovieView
import com.gabriel.themovie.ui.adapters.FilmeAdapter
import com.gabriel.themovie.util.base.BaseFragment
import com.gabriel.themovie.util.constants.ConstantsView.BASE_URL_IMAGES
import com.gabriel.themovie.util.constants.ConstantsView.EXIBE_ELLIPSIZE
import com.gabriel.themovie.util.constants.ConstantsView.LIMIT_DESCRIPTION
import com.gabriel.themovie.util.constants.ConstantsView.LIMIT_NOTA
import com.gabriel.themovie.util.constants.ConstantsView.N_EXIBE_ELLIPSIZE
import com.gabriel.themovie.util.extensions.hide
import com.gabriel.themovie.util.extensions.limitValue
import com.gabriel.themovie.util.extensions.show
import com.gabriel.themovie.util.extensions.toast
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetalhesFragment : BaseFragment<FragmentDetalhesBinding, DetalhesViewModel>() {

    override val viewModel: DetalhesViewModel by viewModel()
    private val args: DetalhesFragmentArgs by navArgs()
    private val filmeAdapter by lazy { FilmeAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configuraRecyclerView()
        buscaDetails()
        multiMovieObserver()
        filmesSimilaresObserver()
    }

    private fun filmesSimilaresObserver() = lifecycleScope.launch {
        viewModel.listSimilares.collect { resources ->
            when (resources) {
                is ResourceState.Success -> {
                    resources.data?.let { results ->
                        filmeAdapter.moviesList = results
                    }
                }
                is ResourceState.Error -> {
                    binding.progressDetalhes.hide()
                    toast("Um erro ocorreu.")
                }
                is ResourceState.Loading -> {
                    binding.progressDetalhes.show()
                }
                else -> {}
            }
        }
    }

    private fun configuraRecyclerView() = with(binding) {
        rvDetalhesSemelhantes.adapter = filmeAdapter
        rvDetalhesSemelhantes.layoutManager = GridLayoutManager(requireContext(), 4)
    }

    private fun buscaDetails() {
        viewModel.getDetail(args.movieView)
    }

    private fun multiMovieObserver() = lifecycleScope.launch {
        viewModel.multiMovieDetail.collect { resource ->
            when (resource) {
                is ResourceState.Success -> {
                    resource.data?.let { result ->
                        binding.progressDetalhes.hide()
                        preencheComponentes(result)
                    }
                }
                is ResourceState.Error -> {
                    binding.progressDetalhes.hide()
                    toast("Um erro ocorreu.")
                }
                is ResourceState.Loading -> {
                    binding.progressDetalhes.show()
                }
                else -> {}
            }
        }
    }

    private fun preencheComponentes(movieView: MovieView) {
        carregaImagens(movieView)
        carregaTitle(movieView)
        carregaNota(movieView)
        carregaDescription(movieView)
        carregaGeneros(movieView)
    }

    private fun carregaGeneros(movieView: MovieView) {
        if (movieView.generos != null) {
            resolveGeneroUm(movieView = movieView)
        }
        if (movieView.generos!!.size >= 2) {
            resolveGeneroDois(movieView = movieView)
        }
    }

    private fun resolveGeneroUm(movieView: MovieView) {
        binding.detalhesContainerGeneroUm.show()
        binding.detalhesGeneroUm.text = movieView.generos!![0]!!.name
    }

    private fun resolveGeneroDois(movieView: MovieView) {
        binding.detalhesContainerGeneroDois.show()
        binding.detalhesGeneroDois.text = movieView.generos!![1]!!.name
    }

    private fun carregaDescription(movieView: MovieView) {
        binding.detalhesDescricao.text =
            movieView.description?.limitValue(LIMIT_DESCRIPTION, EXIBE_ELLIPSIZE)
    }

    private fun carregaNota(movieView: MovieView) {
        binding.detalhesNota.text =
            movieView.nota.toString().limitValue(LIMIT_NOTA, N_EXIBE_ELLIPSIZE)
    }

    private fun carregaTitle(movieView: MovieView) {
        binding.detalhesTitulo.text = movieView.title
    }

    private fun carregaImagens(filmeView: MovieView) {
        binding.imageCartaz.load("${BASE_URL_IMAGES}${filmeView.cartaz}")
        binding.imageBanner.load("${BASE_URL_IMAGES}${filmeView.banner}")
    }

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentDetalhesBinding =
        FragmentDetalhesBinding.inflate(layoutInflater, container, false)
}
