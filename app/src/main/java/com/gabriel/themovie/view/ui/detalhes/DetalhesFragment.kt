package com.gabriel.themovie.view.ui.detalhes

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.gabriel.domain.util.state.ResourceState
import com.gabriel.themovie.R
import com.gabriel.themovie.databinding.FragmentDetalhesBinding
import com.gabriel.themovie.movie.model.MovieView
import com.gabriel.themovie.view.adapters.MovieAdapterPrimary
import com.gabriel.themovie.util.base.BaseFragmentIn
import com.gabriel.themovie.util.constants.ConstantsView
import com.gabriel.themovie.util.constants.ConstantsView.BASE_URL_IMAGES
import com.gabriel.themovie.util.constants.ConstantsView.EXIBE_ELLIPSIZE
import com.gabriel.themovie.util.constants.ConstantsView.LIMIT_DESCRIPTION
import com.gabriel.themovie.util.constants.ConstantsView.LIMIT_NOTA
import com.gabriel.themovie.util.constants.ConstantsView.NOT_EXIBE_ELLIPSIZE
import com.gabriel.themovie.util.constants.ConstantsView.RV_COLUNS_DEFAULT
import com.gabriel.themovie.util.constants.ConstantsView.TYPE_VIDEO
import com.gabriel.themovie.util.extensions.*
import com.gabriel.themovie.video.model.VideoView
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetalhesFragment : BaseFragmentIn<FragmentDetalhesBinding, DetalhesViewModel>() {

    override val viewModel: DetalhesViewModel by viewModel()
    private val args: DetalhesFragmentArgs by navArgs()
    private val movieAdapter by lazy { MovieAdapterPrimary() }
    lateinit var globalMovie: MovieView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configuraRecyclerView()
        getDetails()
        movieObserver()
        similarObserver()
        favoritoObserver()
        configuraClickAdapter()
        configuraClickDialog()
    }

    private fun configuraRecyclerView() = with(binding) {
        rvMoviesSemelhantes.adapter = movieAdapter
        rvMoviesSemelhantes.layoutManager = GridLayoutManager(requireContext(), RV_COLUNS_DEFAULT)
    }

    private fun getDetails() {
        viewModel.getDetail(args.movieView)
    }

    private fun movieObserver() = lifecycleScope.launch {
        viewModel.movieDetail.collect { resource ->
            when (resource) {
                is ResourceState.Success -> {
                    preencheDetails(resource)
                    binding.pbLoadingDetalhes.hide()
                }
                is ResourceState.Error -> {
                    toast(getString(R.string.um_erro_ocorreu))
                    binding.pbLoadingDetalhes.hide()
                }
                is ResourceState.Loading -> {
                    binding.pbLoadingDetalhes.show()
                }
                else -> {}
            }
        }
    }

    private fun preencheDetails(resource: ResourceState<MovieView>) {
        resource.data?.let { movieView ->
            globalMovie = movieView
            carregaImagens(movieView)
            configuraClickTrailer(movieView)
            carregaTitle(movieView)
            carregaNota(movieView)
            carregaDescription(movieView)
            carregaGeneros(movieView)
            carregaFav()
        }
    }

    private fun carregaImagens(movieView: MovieView) {
        binding.ivBanner.tentaCarregar("${BASE_URL_IMAGES}${movieView.banner}")
        binding.ivCartaz.tentaCarregar("${BASE_URL_IMAGES}${movieView.cartaz}")
    }

    private fun configuraClickTrailer(movieView: MovieView) = with(binding) {
        ivPlayTrailer.setOnClickListener {
            goTrailer(movieView.videos)
        }
    }

    private fun goTrailer(videos: List<VideoView>?) {
        if (!videos.isNullOrEmpty()) {
            val videoKey = videos.filter {
                (it.type == TYPE_VIDEO) || (it.official == true)
            }[0].key

            if (!videoKey.isNullOrEmpty()) {
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("${ConstantsView.BASE_URL_VIDEOS}$videoKey")
                ).apply { startActivity(this) }
            }
        } else {
            toast(getString(R.string.movie_sem_trailer))
        }
    }

    private fun carregaTitle(movieView: MovieView) {
        binding.tvTituloMovie.text = movieView.title
    }

    private fun carregaNota(movieView: MovieView) {
        binding.tvNotaMovie.text =
            movieView.nota.toString().limitValue(LIMIT_NOTA, NOT_EXIBE_ELLIPSIZE)
    }

    private fun carregaDescription(movieView: MovieView) {
        movieView.description?.let {
            binding.tvInfoLerMais.show()
            binding.tvDescricaoMovie.text = it.limitValue(LIMIT_DESCRIPTION, EXIBE_ELLIPSIZE)
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
        binding.containerGeneroUm.show()
        binding.tvGeneroUm.text = movieView.generos!![0].name
    }

    private fun resolveGeneroDois(movieView: MovieView) {
        binding.containerGeneroDois.show()
        binding.tvGeneroDois.text = movieView.generos!![1].name
    }

    private fun carregaFav() = lifecycleScope.launch {
        viewModel.verify.collect { resource ->
            binding.cbFavoritarMovie.isChecked = resource
        }
    }

    private fun similarObserver() = lifecycleScope.launch {
        viewModel.listSimilares.collect { resources ->
            when (resources) {
                is ResourceState.Success -> {
                    exibeListaSimilares(resources)
                    binding.pbLoadingDetalhes.hide()
                }
                is ResourceState.Error -> {
                    toast(getString(R.string.um_erro_ocorreu))
                    binding.pbLoadingDetalhes.hide()
                }
                is ResourceState.Loading -> {
                    binding.pbLoadingDetalhes.show()
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

    private fun favoritoObserver() = lifecycleScope.launch {
        salvaMovie()
    }

    private fun salvaMovie() {
        binding.cbFavoritarMovie.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                viewModel.saveFavorito(movieView = globalMovie)
            } else {
                viewModel.deleteMovie(movieView = globalMovie)
            }
        }
    }

    private fun configuraClickAdapter() {
        movieAdapter.setMovieOnClickListener { movieView ->
            movieView.type = globalMovie.type
            val action = DetalhesFragmentDirections
                .acaoSimilaresParaDetalhes(movieView)
            findNavController().navigate(action)
        }
    }

    private fun configuraClickDialog() {
        binding.tvDescricaoMovie.setOnClickListener {
            val action = DetalhesFragmentDirections
                .acaoDetalhesParaDialog(globalMovie)
            findNavController().navigate(action)
        }
    }

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentDetalhesBinding =
        FragmentDetalhesBinding.inflate(layoutInflater, container, false)
}
