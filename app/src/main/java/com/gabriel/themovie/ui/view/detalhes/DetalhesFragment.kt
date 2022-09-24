package com.gabriel.themovie.ui.view.detalhes

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
import com.gabriel.themovie.ui.adapters.MovieAdapterPrimary
import com.gabriel.themovie.util.base.BaseFragment
import com.gabriel.themovie.util.constants.ConstantsView
import com.gabriel.themovie.util.constants.ConstantsView.BASE_URL_IMAGES
import com.gabriel.themovie.util.constants.ConstantsView.RV_COLUNS_DEFAULT
import com.gabriel.themovie.util.constants.ConstantsView.EXIBE_ELLIPSIZE
import com.gabriel.themovie.util.constants.ConstantsView.LIMIT_DESCRIPTION
import com.gabriel.themovie.util.constants.ConstantsView.LIMIT_NOTA
import com.gabriel.themovie.util.constants.ConstantsView.NOT_EXIBE_ELLIPSIZE
import com.gabriel.themovie.util.constants.ConstantsView.TYPE_VIDEO
import com.gabriel.themovie.util.extensions.*
import com.gabriel.themovie.video.model.VideoView
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetalhesFragment : BaseFragment<FragmentDetalhesBinding, DetalhesViewModel>() {

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

    private fun configuraClickDialog() {
        binding.tvDescricaoMovie.setOnClickListener {
            val action = DetalhesFragmentDirections
                .acaoDetalhesParaDialog(globalMovie)
            findNavController().navigate(action)
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

    /**
     * Após fechar o trailer e voltar pra tela de detalhes, o foco da tela estava indo para
     * o fim da recycler view. Isso foi resolvido alterando o [isFocusable] para false.
     *
     * @param isFocusable é o parâmetro de foco.
     */
    private fun configuraRecyclerView() = with(binding) {
        rvMoviesSemelhantes.adapter = movieAdapter
        rvMoviesSemelhantes.layoutManager = GridLayoutManager(requireContext(), RV_COLUNS_DEFAULT)
        rvMoviesSemelhantes.isFocusable = false
    }

    private fun getDetails() {
        viewModel.getDetail(args.movieView)
    }

    private fun movieObserver() = lifecycleScope.launch {
        viewModel.movieDetail.collect { resource ->
            when (resource) {
                is ResourceState.Success -> {
                    preencheDetails(resource)
                    ocultaProgressBar(binding.pbLoadingDetalhes)
                }
                is ResourceState.Error -> {
                    toast(getString(R.string.um_erro_ocorreu))
                    ocultaProgressBar(binding.pbLoadingDetalhes)
                }
                is ResourceState.Loading -> {
                    exibeProgressBar(binding.pbLoadingDetalhes)
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
                    ocultaProgressBar(binding.pbLoadingDetalhes)
                }
                is ResourceState.Error -> {
                    toast(getString(R.string.um_erro_ocorreu))
                    ocultaProgressBar(binding.pbLoadingDetalhes)
                }
                is ResourceState.Loading -> {
                    exibeProgressBar(binding.pbLoadingDetalhes)
                }
                else -> {}
            }
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
            toast("Este movie não possui vídeo.")
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

    private fun carregaDescription(movieView: MovieView) {
        movieView.description?.let {
            binding.tvInfoLerMais.show()
            binding.tvDescricaoMovie.text = it.limitValue(LIMIT_DESCRIPTION, EXIBE_ELLIPSIZE)
        }
    }

    private fun carregaNota(movieView: MovieView) {
        binding.tvNotaMovie.text =
            movieView.nota.toString().limitValue(LIMIT_NOTA, NOT_EXIBE_ELLIPSIZE)
    }

    private fun carregaTitle(movieView: MovieView) {
        binding.tvTituloMovie.text = movieView.title
    }

    private fun carregaImagens(movieView: MovieView) {
        binding.ivBanner.tentaCarregar("${BASE_URL_IMAGES}${movieView.banner}")
        binding.ivCartaz.tentaCarregar("${BASE_URL_IMAGES}${movieView.cartaz}")
    }

    private fun carregaFav() = lifecycleScope.launch {
        viewModel.verify.collect { resource ->
            binding.cbFavoritarMovie.isChecked = resource
        }
    }

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentDetalhesBinding =
        FragmentDetalhesBinding.inflate(layoutInflater, container, false)
}
