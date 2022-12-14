package com.gabriel.themovie.view.ui.filmes

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
import com.gabriel.themovie.databinding.FragmentFilmesBinding
import com.gabriel.themovie.movie.model.MovieView
import com.gabriel.themovie.view.adapters.MovieAdapterPrimary
import com.gabriel.themovie.util.base.BaseFragmentIn
import com.gabriel.themovie.util.constants.ConstantsView
import com.gabriel.themovie.util.constants.ConstantsView.BASE_URL_VIDEOS
import com.gabriel.themovie.util.constants.ConstantsView.RV_COLUNS_DEFAULT
import com.gabriel.themovie.util.constants.ConstantsView.TYPE_FILME
import com.gabriel.themovie.util.constants.ConstantsView.TYPE_VIDEO
import com.gabriel.themovie.util.extensions.hide
import com.gabriel.themovie.util.extensions.show
import com.gabriel.themovie.util.extensions.tentaCarregar
import com.gabriel.themovie.util.extensions.toast
import com.gabriel.themovie.video.model.VideoView
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

class FilmesFragment : BaseFragmentIn<FragmentFilmesBinding, FilmesViewModel>() {

    override val viewModel: FilmesViewModel by viewModel()
    private val movieAdapterPrimary by lazy { MovieAdapterPrimary() }
    lateinit var globalMovie: MovieView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configuraRecyclerView()
        observerListaFilmes()
        observerFilmePrincipal()
        configuraClickAdapter()
        configuraClickFilmePrincipal()
    }

    private fun configuraRecyclerView() = with(binding) {
        rvFilme.adapter = movieAdapterPrimary
        rvFilme.layoutManager = GridLayoutManager(requireContext(), RV_COLUNS_DEFAULT)
    }

    private fun observerListaFilmes() = lifecycleScope.launch {
        viewModel.list.collect { resource ->
            when (resource) {
                is ResourceState.Success -> {
                    exibeFilmesPopulares(resource)
                    binding.pbFilme.hide()
                }
                is ResourceState.Error -> {
                    binding.pbFilme.hide()
                    toast(getString(R.string.um_erro_ocorreu))
                    Timber.tag("FilmesFragment/observerListaFilmes")
                        .e("Error -> ${resource.message} Cod -> ${resource.cod}")
                }
                is ResourceState.Loading -> {
                    binding.pbFilme.show()
                }
                else -> {}
            }
        }
    }

    private fun exibeFilmesPopulares(resource: ResourceState<List<MovieView>>) {
        resource.data?.let { results ->
            movieAdapterPrimary.moviesList = results
        }
    }

    private fun observerFilmePrincipal() = lifecycleScope.launch {
        viewModel.movieDetail.collect { resource ->
            when (resource) {
                is ResourceState.Success -> {
                    binding.pbFilme.hide()
                    preencheFilmePrincipal(resource.data)
                }
                is ResourceState.Error -> {
                    toast(getString(R.string.um_erro_ocorreu))
                    binding.pbFilme.hide()
                    Timber.tag("FilmesFragment/observerFilmePrincipal")
                        .e("Error -> ${resource.message} Cod -> ${resource.cod}")
                }
                is ResourceState.Loading -> {
                    binding.pbFilme.show()
                }
                else -> {}
            }
        }
    }

    private fun preencheFilmePrincipal(movieView: MovieView?) {
        movieView?.let { movie ->
            inicializaGlobalMultiMovie(movie)
            carregaImagem(movie)
            carregaTitulo(movie)
            carregaActionTrailer(movie)
            observerActionAdd()
        }
    }

    /**
     * Inicializando um objeto global para utilizar como argumento ao abrir os
     * detalhes do movie principal. Isso pois eu n??o tenho acesso ao [objeto]
     * ao clicar no movie principal.
     *
     * @param globalMovie ?? o objeto global.
     * @param movieView ?? o objeto inicializado.
     */
    private fun inicializaGlobalMultiMovie(movieView: MovieView) {
        globalMovie = movieView
    }

    private fun carregaImagem(movie: MovieView) {
        binding.ivBannerFilmePrincipal
            .tentaCarregar("${ConstantsView.BASE_URL_IMAGES}${movie.cartaz}")
    }

    private fun carregaTitulo(movie: MovieView) {
        binding.tituloFilmePrincipal.text = movie.title
    }

    private fun carregaActionTrailer(movie: MovieView) {
        binding.includeActionsPrincipal.containerTrailer.setOnClickListener {
            goTrailer(movie.videos)
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
                    Uri.parse("${BASE_URL_VIDEOS}$videoKey")
                ).apply { startActivity(this) }
            }
        } else {
            toast(getString(R.string.movie_sem_video))
        }
    }

    private fun observerActionAdd() = lifecycleScope.launch {
        viewModel.verify.collect { ifExists ->
            if (ifExists) {
                binding.includeActionsPrincipal.btnAddFav.load(R.drawable.ic_remove)
            } else {
                binding.includeActionsPrincipal.btnAddFav.load(R.drawable.ic_add)
            }
        }
    }

    private fun configuraClickAdapter() {
        movieAdapterPrimary.setMovieOnClickListener { movieView ->
            actionGoDetails(entity = movieView)
        }
    }

    private fun configuraClickFilmePrincipal() = with(binding) {
        actionFilmePrincipalGoDetails()
        actionFilmePrincipalSave()
    }

    private fun FragmentFilmesBinding.actionFilmePrincipalGoDetails() {
        includeActionsPrincipal.btnLerMais.setOnClickListener {
            actionGoDetails(globalMovie)
        }
    }

    private fun FragmentFilmesBinding.actionFilmePrincipalSave() {
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
        entity.type = TYPE_FILME
        val action = FilmesFragmentDirections
            .acaoFilmesParaDetalhes(entity)
        findNavController().navigate(action)
    }

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentFilmesBinding =
        FragmentFilmesBinding.inflate(layoutInflater, container, false)
}
