package com.gabriel.themovie.ui.view.series

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import coil.load
import com.gabriel.domain.util.state.ResourceState
import com.gabriel.themovie.R
import com.gabriel.themovie.databinding.FragmentSeriesBinding
import com.gabriel.themovie.movie.model.MovieView
import com.gabriel.themovie.ui.adapters.MovieAdapterPrimary
import com.gabriel.themovie.ui.view.detalhes.DetalhesViewModel
import com.gabriel.themovie.util.base.BaseFragment
import com.gabriel.themovie.util.constants.ConstantsView
import com.gabriel.themovie.util.constants.ConstantsView.BASE_URL_VIDEOS
import com.gabriel.themovie.util.constants.ConstantsView.RV_COLUNS_DEFAULT
import com.gabriel.themovie.util.constants.ConstantsView.TYPE_SERIE
import com.gabriel.themovie.util.extensions.hide
import com.gabriel.themovie.util.extensions.show
import com.gabriel.themovie.util.extensions.toast
import com.gabriel.themovie.video.model.VideoView
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

class SeriesFragment : BaseFragment<FragmentSeriesBinding, SeriesViewModel>() {

    override val viewModel: SeriesViewModel by viewModel()
    private val viewModelDetail: DetalhesViewModel by viewModel()
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
                    ocultaProgressBar(binding.progressSerie)
                }
                is ResourceState.Error -> {
                    ocultaProgressBar(binding.progressSerie)
                    toast("Um erro ocorreu: ${resources.message}")
                    Timber.tag("SeriesFragment/observerListaSeries")
                        .e("Error -> ${resources.message} Cod -> ${resources.cod}")
                }
                is ResourceState.Loading -> {
                    exibeProgressBar(binding.progressSerie)
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
                    ocultaProgressBar(binding.progressSerie)
                    preencheSeriePrincipal(resource.data)
                }
                is ResourceState.Error -> {
                    toast(getString(R.string.um_erro_ocorreu))
                    ocultaProgressBar(binding.progressSerie)
                    exibeImagemDefault(binding.serieBannerPrincipal)

                    Timber.tag("FilmesFragment/observerFilmePrincipal")
                        .e("Error -> ${resource.message} Cod -> ${resource.cod}")
                }
                is ResourceState.Loading -> {
                    exibeProgressBar(binding.progressSerie)
                    exibeImagemDefault(binding.serieBannerPrincipal)
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
            carregaActionTrailer(movie)
        }
    }

    private fun carregaImagem(movie: MovieView) {
        binding.serieBannerPrincipal
            .load("${ConstantsView.BASE_URL_IMAGES}${movie.banner}")
    }

    private fun carregaTitulo(movie: MovieView) {
        binding.serieTituloPrincipal.text = movie.title
    }

    private fun carregaActionTrailer(movie: MovieView) {
        binding.includeActionsPrincipal.containerTrailer.setOnClickListener {
            goTrailer(movie.videos)
        }
    }

    /**
     * 1. É verificado se a lista de vídeos não é nula ou vazia.
     * 2. Caso seja, é exibido o toast informativo.
     * 3. É obtido a [key] do video index 0 que seja do tipo [Trailer] e seja [official]
     * 4. É executado a Intent somente se a [key] não é nula.
     */
    private fun goTrailer(videos: List<VideoView>?) {
        if (!videos.isNullOrEmpty()) {
            val videoKey = videos.filter {
                it.type == "Trailer" && it.official == true
            }[0].key

            if (!videoKey.isNullOrEmpty()) {
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("${BASE_URL_VIDEOS}$videoKey")
                ).apply { startActivity(this) }
            }
        } else {
            toast("Este movie não possui vídeo.")
        }
    }

    private fun exibeImagemDefault(image: ImageView) {
        image.load(androidx.appcompat.R.color.material_grey_600)
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
    private fun inicializaGlobalMultiMovie(movieView: MovieView) {
        globalMovie = movieView
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
            toast("${globalMovie.title} salvo com sucesso.")
            viewModelDetail.saveFavorito(globalMovie)
        }
    }

    /**
     * Função global de acesso a tela de Detalhes.
     *
     * @param entity é o argumento esperado pela tela de Detalhes.
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
