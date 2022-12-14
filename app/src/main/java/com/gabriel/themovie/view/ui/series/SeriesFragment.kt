package com.gabriel.themovie.view.ui.series

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import coil.load
import com.gabriel.domain.util.state.ResourceState
import com.gabriel.themovie.R
import com.gabriel.themovie.databinding.FragmentSeriesBinding
import com.gabriel.themovie.movie.model.MovieView
import com.gabriel.themovie.view.adapters.MovieAdapterPrimary
import com.gabriel.themovie.util.base.BaseFragmentIn
import com.gabriel.themovie.util.constants.ConstantsView
import com.gabriel.themovie.util.constants.ConstantsView.RV_COLUNS_DEFAULT
import com.gabriel.themovie.util.constants.ConstantsView.TYPE_SERIE
import com.gabriel.themovie.util.extensions.hide
import com.gabriel.themovie.util.extensions.show
import com.gabriel.themovie.util.extensions.tentaCarregar
import com.gabriel.themovie.util.extensions.toast
import com.gabriel.themovie.video.model.VideoView
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

class SeriesFragment : BaseFragmentIn<FragmentSeriesBinding, SeriesViewModel>() {

    override val viewModel: SeriesViewModel by viewModel()
    private val serieAdapter by lazy { MovieAdapterPrimary() }
    lateinit var globalMovie: MovieView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configuraRecyclerView()
        configuraClickAdapter()
        observerListaSeries()
        observerSeriePrincipal()
        configuraClickSeriePrincipal()
    }

    private fun configuraRecyclerView() = with(binding) {
        rvSerie.adapter = serieAdapter
        rvSerie.layoutManager = GridLayoutManager(requireContext(), RV_COLUNS_DEFAULT)
    }

    private fun configuraClickAdapter() {
        serieAdapter.setMovieOnClickListener { movieView ->
            actionGoDetails(entity = movieView)
        }
    }

    private fun observerListaSeries() = lifecycleScope.launch {
        viewModel.list.collect { resources ->
            when (resources) {
                is ResourceState.Success -> {
                    exibeSeriesPopulares(resources)
                    binding.pbSerie.hide()
                }
                is ResourceState.Error -> {
                    binding.pbSerie.hide()
                    toast("Um erro ocorreu: ${resources.message}")
                    Timber.tag("SeriesFragment/observerListaSeries")
                        .e("Error -> ${resources.message} Cod -> ${resources.cod}")
                }
                is ResourceState.Loading -> {
                    binding.pbSerie.show()
                }
                else -> {}
            }
        }
    }

    private fun exibeSeriesPopulares(resources: ResourceState<List<MovieView>>) {
        resources.data?.let { results ->
            serieAdapter.moviesList = results
        }
    }

    private fun observerSeriePrincipal() = lifecycleScope.launch {
        viewModel.movieDetail.collect { resource ->
            when (resource) {
                is ResourceState.Success -> {
                    binding.pbSerie.hide()
                    preencheSeriePrincipal(resource.data)
                }
                is ResourceState.Error -> {
                    binding.pbSerie.hide()
                    toast(getString(R.string.um_erro_ocorreu))
                    Timber.tag("FilmesFragment/observerFilmePrincipal")
                        .e("Error -> ${resource.message} Cod -> ${resource.cod}")
                }
                is ResourceState.Loading -> {
                    binding.pbSerie.show()
                }
                else -> {}
            }
        }
    }

    private fun preencheSeriePrincipal(movieView: MovieView?) {
        movieView?.let { movie ->
            inicializaGlobalMultiMovie(movie)
            carregaImagem(movie)
            carregaTitulo(movie)
            observerActionAdd()
            carregaActionTrailer(movie)
        }
    }

    /**
     * Inicializando um objeto global para utilizar ao abrir os detalhes do movie principal.
     * Isso pois eu n??o tenho acesso ao [objeto] ao clicar no movie principal.
     *
     * @param globalMovie ?? o objeto global.
     * @param movieView ?? o objeto inicializado.
     */
    private fun inicializaGlobalMultiMovie(movieView: MovieView) {
        globalMovie = movieView
    }

    private fun carregaImagem(movie: MovieView) {
        binding.serieBannerPrincipal
            .tentaCarregar("${ConstantsView.BASE_URL_IMAGES}${movie.banner}")
    }

    private fun carregaTitulo(movie: MovieView) {
        binding.serieTituloPrincipal.text = movie.title
    }

    private fun observerActionAdd() = lifecycleScope.launch {
        viewModel.verify.collect { resource ->
            if (resource) {
                binding.includeActionsPrincipal.btnAddFav.load(R.drawable.ic_remove)
            } else {
                binding.includeActionsPrincipal.btnAddFav.load(R.drawable.ic_add)
            }
        }
    }

    private fun carregaActionTrailer(movie: MovieView) {
        binding.includeActionsPrincipal.containerTrailer.setOnClickListener {
            goTrailer(movie.videos)
        }
    }

    private fun goTrailer(videos: List<VideoView>?) {
        if (!videos.isNullOrEmpty()) {
            val videoKey = videos.filter {
                (it.type == ConstantsView.TYPE_VIDEO) || (it.official == true)
            }[0].key

            if (!videoKey.isNullOrEmpty()) {
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("${ConstantsView.BASE_URL_VIDEOS}$videoKey")
                ).apply { startActivity(this) }
            }
        } else {
            toast(getString(R.string.movie_sem_video))
        }
    }

    private fun configuraClickSeriePrincipal() = with(binding) {
        actionSeriePrincipalGoDetails()
        actionSeriePrincipalSave()
    }

    private fun FragmentSeriesBinding.actionSeriePrincipalGoDetails() {
        includeActionsPrincipal.btnLerMais.setOnClickListener {
            actionGoDetails(globalMovie)
        }
    }

    private fun FragmentSeriesBinding.actionSeriePrincipalSave() {
        includeActionsPrincipal.btnAddFav.setOnClickListener {
            if (viewModel.verify.value) {
                viewModel.deleteMovie(globalMovie)
                toast("${globalMovie.title} removido dos favoritos.")
            } else {
                viewModel.saveFavorito(globalMovie)
                toast("${globalMovie.title} adicionado aos favoritos.")
            }
        }
    }

    /**
     * Fun????o global de acesso a tela de Detalhes.
     *
     * @param entity ?? o argumento esperado pela tela de Detalhes.
     */
    private fun actionGoDetails(entity: MovieView) {
        entity.type = TYPE_SERIE
        val action = SeriesFragmentDirections
            .acaoSeriesParaDetalhes(entity)
        findNavController().navigate(action)
    }

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentSeriesBinding =
        FragmentSeriesBinding.inflate(layoutInflater, container, false)
}
