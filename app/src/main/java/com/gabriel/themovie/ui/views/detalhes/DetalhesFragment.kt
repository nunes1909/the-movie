package com.gabriel.themovie.ui.views.detalhes

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
import com.gabriel.themovie.model.multiMovie.model.MultiMovie
import com.gabriel.themovie.ui.adapters.FilmeAdapter
import com.gabriel.themovie.ui.adapters.SerieAdapter
import com.gabriel.themovie.util.base.BaseFragment
import com.gabriel.themovie.util.constants.ConstantsView.BASE_URL_IMAGES
import com.gabriel.themovie.util.constants.ConstantsView.EXIBE_ELLIPSIZE
import com.gabriel.themovie.util.constants.ConstantsView.LIMIT_DESCRIPTION
import com.gabriel.themovie.util.constants.ConstantsView.LIMIT_NOTA
import com.gabriel.themovie.util.constants.ConstantsView.N_EXIBE_ELLIPSIZE
import com.gabriel.themovie.util.constants.ConstantsView.TYPE_FILME
import com.gabriel.themovie.util.constants.ConstantsView.TYPE_SERIE
import com.gabriel.themovie.util.extensions.hide
import com.gabriel.themovie.util.extensions.limitValue
import com.gabriel.themovie.util.extensions.show
import com.gabriel.themovie.util.extensions.toast
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

class DetalhesFragment : BaseFragment<FragmentDetalhesBinding, DetalhesViewModel>() {

    override val viewModel: DetalhesViewModel by viewModel()
    private val args: DetalhesFragmentArgs by navArgs()
    private val filmeAdapter by lazy { FilmeAdapter() }
    private val serieAdapter by lazy { SerieAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configuraRecyclerView()
        buscaDetails()
        multiMovieObserver()
        filmesSimilaresObserver()
        seriesSimilaresObserver()
    }

    private fun seriesSimilaresObserver() = lifecycleScope.launch {
        viewModel.listSeriesSimilares.collect { resources ->
            when (resources) {
                is ResourceState.Success -> {
                    resources.data?.let { results ->
                        serieAdapter.seriesList = results
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

    private fun filmesSimilaresObserver() = lifecycleScope.launch {
        viewModel.listFilmesSimilares.collect { resources ->
            when (resources) {
                is ResourceState.Success -> {
                    resources.data?.let { results ->
                        filmeAdapter.filmesList = results
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

    /**
     * O adapter da recycler view é definido com When pois a feature de Detalhes recebe
     * duas listas com models diferentes.
     *
     * Caso por algum motivo o [type] venha null, a opção Else se encarrega de não ocorrer
     * uma exception e informar através do Timber o motivo da recycler não carregar.
     */
    private fun configuraRecyclerView() = with(binding) {
        when (args.movie.type) {
            TYPE_FILME -> {
                rvDetalhesSemelhantes.adapter = filmeAdapter
            }
            TYPE_SERIE -> {
                rvDetalhesSemelhantes.adapter = serieAdapter
            }
            else -> {
                Timber
                    .tag("DetalhesFragment/configuraRecyclerView")
                    .e("Erro ao definir o adapter. [Type] não reconhecido.")
            }
        }
        rvDetalhesSemelhantes.layoutManager = GridLayoutManager(requireContext(), 4)
    }

    private fun buscaDetails() {
        viewModel.getDetail(args.movie)
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

    private fun preencheComponentes(multiMovie: MultiMovie) {
        carregaImagens(multiMovie)
        carregaTitle(multiMovie)
        carregaNota(multiMovie)
        carregaDescription(multiMovie)
        carregaGeneros(multiMovie)
    }

    private fun carregaGeneros(multiMovie: MultiMovie) {
        if (multiMovie.generos != null) {
            resolveGeneroUm(multiMovie = multiMovie)
        }
        if (multiMovie.generos!!.size >= 2) {
            resolveGeneroDois(multiMovie = multiMovie)
        }
    }

    private fun resolveGeneroUm(multiMovie: MultiMovie) {
        binding.detalhesContainerGeneroUm.show()
        binding.detalhesGeneroUm.text = multiMovie.generos!![0]!!.name
    }

    private fun resolveGeneroDois(multiMovie: MultiMovie) {
        binding.detalhesContainerGeneroDois.show()
        binding.detalhesGeneroDois.text = multiMovie.generos!![1]!!.name
    }

    private fun carregaDescription(multiMovie: MultiMovie) {
        binding.detalhesDescricao.text =
            multiMovie.description?.limitValue(LIMIT_DESCRIPTION, EXIBE_ELLIPSIZE)
    }

    private fun carregaNota(multiMovie: MultiMovie) {
        binding.detalhesNota.text =
            multiMovie.nota.toString().limitValue(LIMIT_NOTA, N_EXIBE_ELLIPSIZE)
    }

    private fun carregaTitle(multiMovie: MultiMovie) {
        binding.detalhesTitulo.text = multiMovie.title
    }

    private fun carregaImagens(filmeView: MultiMovie) {
        binding.imageCartaz.load("${BASE_URL_IMAGES}${filmeView.cartaz}")
        binding.imageBanner.load("${BASE_URL_IMAGES}${filmeView.banner}")
    }

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentDetalhesBinding =
        FragmentDetalhesBinding.inflate(layoutInflater, container, false)
}
