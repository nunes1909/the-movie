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
import com.gabriel.themovie.R
import com.gabriel.themovie.databinding.FragmentDetalhesBinding
import com.gabriel.themovie.movie.model.MovieView
import com.gabriel.themovie.ui.adapters.MovieAdapter
import com.gabriel.themovie.util.base.BaseFragment
import com.gabriel.themovie.util.constants.ConstantsView.BASE_URL_IMAGES
import com.gabriel.themovie.util.constants.ConstantsView.RV_COLUNS_DEFAULT
import com.gabriel.themovie.util.constants.ConstantsView.EXIBE_ELLIPSIZE
import com.gabriel.themovie.util.constants.ConstantsView.LIMIT_DESCRIPTION
import com.gabriel.themovie.util.constants.ConstantsView.LIMIT_NOTA
import com.gabriel.themovie.util.constants.ConstantsView.NOT_EXIBE_ELLIPSIZE
import com.gabriel.themovie.util.extensions.hide
import com.gabriel.themovie.util.extensions.limitValue
import com.gabriel.themovie.util.extensions.show
import com.gabriel.themovie.util.extensions.toast
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetalhesFragment : BaseFragment<FragmentDetalhesBinding, DetalhesViewModel>() {

    override val viewModel: DetalhesViewModel by viewModel()
    private val args: DetalhesFragmentArgs by navArgs()
    private val movieAdapter by lazy { MovieAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configuraRecyclerView()
        getDetails()
        movieObserver()
        similarObserver()
    }

    private fun configuraRecyclerView() = with(binding) {
        rvDetalhesSemelhantes.adapter = movieAdapter
        rvDetalhesSemelhantes.layoutManager = GridLayoutManager(requireContext(), RV_COLUNS_DEFAULT)
    }

    private fun getDetails() {
        viewModel.getDetail(args.movieView)
    }

    private fun movieObserver() = lifecycleScope.launch {
        viewModel.movieDetail.collect { resource ->
            when (resource) {
                is ResourceState.Success -> {
                    preencheDetails(resource)
                    ocultaProgressBar(binding.progressDetalhes)
                }
                is ResourceState.Error -> {
                    toast(getString(R.string.um_erro_ocorreu))
                    ocultaProgressBar(binding.progressDetalhes)
                }
                is ResourceState.Loading -> {
                    exibeProgressBar(binding.progressDetalhes)
                }
                else -> {}
            }
        }
    }

    private fun similarObserver() = lifecycleScope.launch {
        viewModel.listSimilares.collect { resources ->
            when (resources) {
                is ResourceState.Success -> {
                    exibeListaSimilares(resources)
                    ocultaProgressBar(binding.progressDetalhes)
                }
                is ResourceState.Error -> {
                    toast(getString(R.string.um_erro_ocorreu))
                    ocultaProgressBar(binding.progressDetalhes)
                }
                is ResourceState.Loading -> {
                    exibeProgressBar(binding.progressDetalhes)
                }
                else -> {}
            }
        }
    }

    private fun exibeListaSimilares(resources: ResourceState<List<MovieView>>) {
        resources.data?.let { results ->
            movieAdapter.moviesList = results
        }
    }

    private fun ocultaProgressBar(progress: View) {
        progress.hide()
    }

    private fun exibeProgressBar(progress: View) {
        progress.show()
    }

    private fun preencheDetails(resource: ResourceState<MovieView>) {
        resource.data?.let { movieView ->
            carregaImagens(movieView)
            carregaTitle(movieView)
            carregaNota(movieView)
            carregaDescription(movieView)
            carregaGeneros(movieView)
        }
    }

    private fun carregaGeneros(movieView: MovieView) {
        when (movieView.generos?.size) {
            1 -> {
                resolveGeneroUm(movieView = movieView)
            }
            2 -> {
                resolveGeneroUm(movieView = movieView)
                resolveGeneroDois(movieView = movieView)
            }
            else -> {}
        }
    }

    private fun resolveGeneroUm(movieView: MovieView) {
        binding.detalhesContainerGeneroUm.show()
        binding.detalhesGeneroUm.text = movieView.generos!![0].name
    }

    private fun resolveGeneroDois(movieView: MovieView) {
        binding.detalhesContainerGeneroDois.show()
        binding.detalhesGeneroDois.text = movieView.generos!![1].name
    }

    private fun carregaDescription(movieView: MovieView) {
        binding.detalhesDescricao.text =
            movieView.description?.limitValue(LIMIT_DESCRIPTION, EXIBE_ELLIPSIZE)
    }

    private fun carregaNota(movieView: MovieView) {
        binding.detalhesNota.text =
            movieView.nota.toString().limitValue(LIMIT_NOTA, NOT_EXIBE_ELLIPSIZE)
    }

    private fun carregaTitle(movieView: MovieView) {
        binding.detalhesTitulo.text = movieView.title
    }

    private fun carregaImagens(filmeView: MovieView) {
        binding.imageBanner.load("${BASE_URL_IMAGES}${filmeView.banner}")
        binding.imageCartaz.load("${BASE_URL_IMAGES}${filmeView.cartaz}")
    }

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentDetalhesBinding =
        FragmentDetalhesBinding.inflate(layoutInflater, container, false)
}
