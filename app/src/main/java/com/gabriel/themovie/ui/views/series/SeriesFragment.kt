package com.gabriel.themovie.ui.views.series

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
import com.gabriel.themovie.databinding.FragmentSeriesBinding
import com.gabriel.themovie.model.multiMovie.mapper.MultiMovieMapperFilme
import com.gabriel.themovie.model.multiMovie.mapper.MultiMovieMapperSerie
import com.gabriel.themovie.model.multiMovie.model.MultiMovie
import com.gabriel.themovie.model.serie.model.SerieView
import com.gabriel.themovie.ui.adapters.SerieAdapter
import com.gabriel.themovie.ui.views.filmes.FilmesFragmentDirections
import com.gabriel.themovie.util.base.BaseFragment
import com.gabriel.themovie.util.constants.ConstantsView
import com.gabriel.themovie.util.extensions.hide
import com.gabriel.themovie.util.extensions.show
import com.gabriel.themovie.util.extensions.toast
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

class SeriesFragment : BaseFragment<FragmentSeriesBinding, SeriesViewModel>() {

    override val viewModel: SeriesViewModel by viewModel()
    private val serieAdapter by lazy { SerieAdapter() }
    private val multiMovieMapperSerie by lazy { MultiMovieMapperSerie() }
    lateinit var globalMultiMovie: MultiMovie

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configuraRecyclerView()
        configuraClickAdapter()
        observerListaSeries()
        observerSeriePrincipal()
        configuraClickSeriePrincipal()
    }

    private fun configuraClickSeriePrincipal() = with(binding) {
        actionSeriePrincipalGoDetails()
        actionSeriePrincipalSave()
    }

    private fun FragmentSeriesBinding.actionSeriePrincipalSave() {
        buttonAddFav.btnAddFav.setOnClickListener {
            // implementar save
        }
    }

    private fun FragmentSeriesBinding.actionSeriePrincipalGoDetails() {
        buttonLerMais.btnLerMais.setOnClickListener {
            actionGoDetails(globalMultiMovie)
        }
    }

    private fun configuraClickAdapter() {
        serieAdapter.setSerieOnClickListener { serieView ->
            val multiMovie = multiMovieMapperSerie.mapFromDomain(serieView)
            actionGoDetails(multiMovie)
        }
    }

    /**
     * Metodo universal com a ação de ir para a feature de Detalhes.
     *
     * @param entity é a entidade solicitada pela feature Detalhes.
     * @param action é a ação do Navigation para mudar de tela.
     */
    private fun actionGoDetails(entity: MultiMovie) {
        val action = SeriesFragmentDirections
            .acaoSeriesParaDetalhes(entity)
        findNavController().navigate(action)
    }

    private fun observerSeriePrincipal() = lifecycleScope.launch {
        viewModel.listTranding.collect { resource ->
            when (resource) {
                is ResourceState.Success -> {
                    resource.data?.let { results ->
                        val serieView = results[0]
                        binding.serieBannerPrincipal
                            .load("${ConstantsView.BASE_URL_IMAGES}${serieView.banner}")

                        binding.serieTituloPrincipal.text = serieView.title
                        inicializaGlobalMultiMovie(serieView)
                    }
                }
                is ResourceState.Error -> {
                    binding.serieBannerPrincipal.load(R.drawable.erro)
                    Timber.tag("FilmesFragment/observerFilmePrincipal")
                        .e("Error -> ${resource.message} Cod -> ${resource.cod}")
                }
                is ResourceState.Loading -> {
                    binding.serieBannerPrincipal.load(androidx.appcompat.R.color.material_grey_600)
                }
                else -> {}
            }
        }
    }

    private fun inicializaGlobalMultiMovie(serieView: SerieView) {
        globalMultiMovie = multiMovieMapperSerie.mapFromDomain(serieView)
    }

    private fun observerListaSeries() = lifecycleScope.launch {
        viewModel.list.collect { resources ->
            when (resources) {
                is ResourceState.Success -> {
                    resources.data?.let { results ->
                        binding.progressSerie.hide()
                        serieAdapter.seriesList = results
                    }
                }
                is ResourceState.Error -> {
                    binding.progressSerie.hide()
                    toast("Um erro ocorreu: ${resources.message}")
                    Timber.tag("SeriesFragment/observerListaSeries")
                        .e("Error -> ${resources.message} Cod -> ${resources.cod}")
                }
                is ResourceState.Loading -> {
                    binding.progressSerie.show()
                }
                else -> {}
            }
        }
    }

    private fun configuraRecyclerView() = with(binding) {
        rvSerie.adapter = serieAdapter
        rvSerie.layoutManager = GridLayoutManager(requireContext(), 4)
    }

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentSeriesBinding =
        FragmentSeriesBinding.inflate(layoutInflater, container, false)
}
