package com.gabriel.themovie.ui.views.detalhes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import coil.load
import com.gabriel.domain.util.state.ResourceState
import com.gabriel.themovie.databinding.FragmentDetalhesBinding
import com.gabriel.themovie.model.filme.model.FilmeView
import com.gabriel.themovie.util.base.BaseFragment
import com.gabriel.themovie.util.constants.ConstantsView.BASE_URL_IMAGES
import com.gabriel.themovie.util.constants.ConstantsView.EXIBE_ELLIPSIZE
import com.gabriel.themovie.util.constants.ConstantsView.LIMIT_DESCRIPTION
import com.gabriel.themovie.util.constants.ConstantsView.LIMIT_NOTA
import com.gabriel.themovie.util.constants.ConstantsView.N_EXIBE_ELLIPSIZE
import com.gabriel.themovie.util.extensions.hide
import com.gabriel.themovie.util.extensions.limitValue
import com.gabriel.themovie.util.extensions.show
import com.gabriel.themovie.util.extensions.toast
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetalhesFragment : BaseFragment<FragmentDetalhesBinding, DetalhesViewModel>() {

    override val viewModel: DetalhesViewModel by viewModel()
    private val args: DetalhesFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        buscaDetails()
        filmesObserver()
    }

    private fun buscaDetails() {
        viewModel.getDetail(args.movie)
    }

    private fun filmesObserver() = lifecycleScope.launch {
        viewModel.filmeViewDetail.collect { resource ->
            when (resource) {
                is ResourceState.Success -> {
                    resource.data?.let { result ->
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

    private fun preencheComponentes(filmeView: FilmeView) = with(binding) {
        progressDetalhes.hide()
        imageCartaz.load("${BASE_URL_IMAGES}${filmeView.cartaz}")
        imageBanner.load("${BASE_URL_IMAGES}${filmeView.banner}")
        detalhesTitulo.text = filmeView.title
        detalhesNota.text = filmeView.nota.toString().limitValue(LIMIT_NOTA, N_EXIBE_ELLIPSIZE)
        detalhesDescricao.text =
            filmeView.description?.limitValue(LIMIT_DESCRIPTION, EXIBE_ELLIPSIZE)
        filmeView.generos?.get(0)?.let { genero ->
            detalhesGeneroUm.show()
            detalhesGeneroUm.text = genero.name
        }
        filmeView.generos?.get(1)?.let { genero ->
            detalhesGeneroDois.show()
            detalhesGeneroDois.text = genero.name
        }
    }

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentDetalhesBinding =
        FragmentDetalhesBinding.inflate(layoutInflater, container, false)
}
