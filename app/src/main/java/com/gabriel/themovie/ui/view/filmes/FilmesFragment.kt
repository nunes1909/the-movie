package com.gabriel.themovie.ui.view.filmes

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
import com.gabriel.themovie.databinding.FragmentFilmesBinding
import com.gabriel.themovie.movie.model.MovieView
import com.gabriel.themovie.ui.adapters.MovieAdapter
import com.gabriel.themovie.util.base.BaseFragment
import com.gabriel.themovie.util.constants.ConstantsView
import com.gabriel.themovie.util.constants.ConstantsView.RV_COLUNS_DEFAULT
import com.gabriel.themovie.util.constants.ConstantsView.TYPE_FILME
import com.gabriel.themovie.util.extensions.hide
import com.gabriel.themovie.util.extensions.show
import com.gabriel.themovie.util.extensions.toast
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

class FilmesFragment : BaseFragment<FragmentFilmesBinding, FilmesViewModel>() {

    override val viewModel: FilmesViewModel by viewModel()
    private val movieAdapter by lazy { MovieAdapter() }
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
        rvFilme.adapter = movieAdapter
        rvFilme.layoutManager = GridLayoutManager(requireContext(), RV_COLUNS_DEFAULT)
    }

    private fun configuraClickAdapter() {
        movieAdapter.setFilmeOnClickListener { movieView ->
            actionGoDetails(entity = movieView)
        }
    }

    private fun observerListaFilmes() = lifecycleScope.launch {
        viewModel.list.collect { resource ->
            when (resource) {
                is ResourceState.Success -> {
                    exibeFilmesPopulares(resource)
                    ocultaProgressBar(binding.progressFilme)
                }
                is ResourceState.Error -> {
                    ocultaProgressBar(binding.progressFilme)
                    toast(getString(R.string.um_erro_ocorreu))
                    Timber.tag("FilmesFragment/observerListaFilmes")
                        .e("Error -> ${resource.message} Cod -> ${resource.cod}")
                }
                is ResourceState.Loading -> {
                    exibeProgressBar(binding.progressFilme)
                }
                else -> {}
            }
        }
    }

    private fun exibeFilmesPopulares(resource: ResourceState<List<MovieView>>) {
        resource.data?.let { results ->
            movieAdapter.moviesList = results
        }
    }

    private fun observerFilmePrincipal() = lifecycleScope.launch {
        viewModel.trending.collect { resource ->
            when (resource) {
                is ResourceState.Success -> {
                    ocultaProgressBar(binding.progressFilme)
                    preencheFilmePrincipal(resource)
                }
                is ResourceState.Error -> {
                    toast(getString(R.string.um_erro_ocorreu))
                    ocultaProgressBar(binding.progressFilme)
                    exibeImagemDefault(binding.bannerFilmePrincipal)

                    Timber.tag("FilmesFragment/observerFilmePrincipal")
                        .e("Error -> ${resource.message} Cod -> ${resource.cod}")
                }
                is ResourceState.Loading -> {
                    exibeProgressBar(binding.progressFilme)
                    exibeImagemDefault(binding.bannerFilmePrincipal)
                }
                else -> {}
            }
        }
    }

    private fun preencheFilmePrincipal(resource: ResourceState<List<MovieView>>) {
        resource.data?.let { results ->
            val movie = buscaFilmeMaisVotado(results)
            carregaImagem(movie)
            carregaTitulo(movie)
            inicializaGlobalMultiMovie(movie)
        }
    }

    private fun buscaFilmeMaisVotado(results: List<MovieView>): MovieView {
        return results.sortedBy { it.nota }[0]
    }

    private fun carregaImagem(movie: MovieView) {
        binding.bannerFilmePrincipal
            .load("${ConstantsView.BASE_URL_IMAGES}${movie.cartaz}")
    }

    private fun carregaTitulo(movie: MovieView) {
        binding.tituloFilmePrincipal.text = movie.title
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

    private fun configuraClickFilmePrincipal() = with(binding) {
        actionFilmePrincipalGoDetails()
        actionFilmePrincipalSave()
    }

    private fun FragmentFilmesBinding.actionFilmePrincipalGoDetails() {
        buttonLerMais.btnLerMais.setOnClickListener {
            actionGoDetails(globalMovie)
        }
    }

    private fun FragmentFilmesBinding.actionFilmePrincipalSave() {
        buttonAddFav.btnAddFav.setOnClickListener {
            toast(getString(R.string.salvo_favoritos))
            // implementar save dao
        }
    }

    /**
     * Função global de acesso a tela de Detalhes.
     *
     * @param entity é o argumento esperado pela tela de Detalhes.
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
