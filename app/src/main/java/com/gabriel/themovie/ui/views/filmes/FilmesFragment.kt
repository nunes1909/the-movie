package com.gabriel.themovie.ui.views.filmes

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
import com.gabriel.themovie.model.filme.model.FilmeView
import com.gabriel.themovie.model.multiMovie.mapper.MultiMovieMapperFilme
import com.gabriel.themovie.model.multiMovie.model.MultiMovie
import com.gabriel.themovie.ui.adapters.FilmeAdapter
import com.gabriel.themovie.util.base.BaseFragment
import com.gabriel.themovie.util.constants.ConstantsView
import com.gabriel.themovie.util.extensions.hide
import com.gabriel.themovie.util.extensions.show
import com.gabriel.themovie.util.extensions.toast
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

class FilmesFragment : BaseFragment<FragmentFilmesBinding, FilmesViewModel>() {

    override val viewModel: FilmesViewModel by viewModel()
    private val filmeAdapter by lazy { FilmeAdapter() }
    private val multiMovieMapperFilme by lazy { MultiMovieMapperFilme() }
    lateinit var globalMultiMovie: MultiMovie

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configuraRecyclerView()
        configuraClickAdapter()
        observerListaFilmes()
        observerFilmePrincipal()
        configuraClickFilmePrincipal()
    }

    private fun configuraRecyclerView() = with(binding) {
        rvFilme.apply {
            adapter = filmeAdapter
            layoutManager = GridLayoutManager(requireContext(), 4)
        }
    }

    private fun configuraClickAdapter() {
        filmeAdapter.setFilmeOnClickListener { filmeView ->
            val multiMovie = multiMovieMapperFilme.mapFromDomain(filmeView)
            actionGoDetails(entity = multiMovie)
        }
    }

    /**
     * Metodo universal com a ação de ir para a feature de Detalhes.
     *
     * @param entity é a entidade solicitada pela feature Detalhes.
     * @param action é a ação do Navigation para mudar de tela.
     */
    private fun actionGoDetails(entity: MultiMovie) {
        val action = FilmesFragmentDirections
            .acaoFilmesParaDetalhes(entity)
        findNavController().navigate(action)
    }

    private fun observerListaFilmes() = lifecycleScope.launch {
        viewModel.list.collect { resource ->
            when (resource) {
                is ResourceState.Success -> {
                    resource.data?.let { results ->
                        binding.progressFilme.hide()
                        filmeAdapter.filmesList = results
                    }
                }
                is ResourceState.Error -> {
                    binding.progressFilme.hide()
                    toast("Um erro ocorreu.")
                    Timber.tag("FilmesFragment/observerListaFilmes")
                        .e("Error -> ${resource.message} Cod -> ${resource.cod}")
                }
                is ResourceState.Loading -> {
                    binding.progressFilme.show()
                }
                else -> {}
            }
        }
    }

    private fun observerFilmePrincipal() = lifecycleScope.launch {
        viewModel.listTranding.collect { resource ->
            when (resource) {
                is ResourceState.Success -> {
                    resource.data?.let { results ->
                        val filmeView = results[0]
                        binding.bannerFilmePrincipal
                            .load("${ConstantsView.BASE_URL_IMAGES}${filmeView.cartaz}")

                        binding.tituloFilmePrincipal.text = filmeView.title
                        inicializaGlobalMultiMovie(filmeView)
                    }
                }
                is ResourceState.Error -> {
                    binding.bannerFilmePrincipal.load(R.drawable.erro)
                    Timber.tag("FilmesFragment/observerFilmePrincipal")
                        .e("Error -> ${resource.message} Cod -> ${resource.cod}")
                }
                is ResourceState.Loading -> {
                    binding.bannerFilmePrincipal.load(androidx.appcompat.R.color.material_grey_600)
                }
                else -> {}
            }
        }
    }

    private fun inicializaGlobalMultiMovie(filmeView: FilmeView) {
        globalMultiMovie = multiMovieMapperFilme.mapFromDomain(filmeView)
    }

    private fun configuraClickFilmePrincipal() = with(binding){
        actionFilmePrincipalGoDetails()
        buttonAddFav.btnAddFav.setOnClickListener {
            // implementar save
        }
    }

    private fun FragmentFilmesBinding.actionFilmePrincipalGoDetails() {
        buttonLerMais.btnLerMais.setOnClickListener {
            actionGoDetails(globalMultiMovie)
        }
    }

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentFilmesBinding =
        FragmentFilmesBinding.inflate(layoutInflater, container, false)
}
