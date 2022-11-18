package com.gabriel.themovie.util.di

import com.gabriel.themovie.genero.mapper.GeneroViewMapper
import com.gabriel.themovie.movie.mapper.MovieViewMapper
import com.gabriel.themovie.ui.view.cadastro.CadastroViewModel
import com.gabriel.themovie.ui.view.detalhes.DetalhesViewModel
import com.gabriel.themovie.ui.view.favoritos.FavoritosViewModel
import com.gabriel.themovie.ui.view.filmes.FilmesViewModel
import com.gabriel.themovie.ui.view.login.LoginViewModel
import com.gabriel.themovie.ui.view.pesquisa.PesquisaViewModel
import com.gabriel.themovie.ui.view.series.SeriesViewModel
import com.gabriel.themovie.usuario.mapper.UsuarioViewMapper
import com.gabriel.themovie.video.mapper.VideoViewMapper
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

fun getViewModules() = module {
    // Region Mappers
    factory { GeneroViewMapper() }
    factory { VideoViewMapper() }
    factory {
        MovieViewMapper(
            generoMapper = get(),
            videoMapper = get()
        )
    }
    factory { UsuarioViewMapper() }
    // Endregion

    // Region ViewModels
    viewModel {
        FilmesViewModel(
            getAllMoviesUseCase = get(),
            getTrendingMovieUseCase = get(),
            saveMovieUseCase = get(),
            getDetailMovieUseCase = get(),
            verifyExists = get(),
            deleteMovieUseCase = get(),
            mapper = get()
        )
    }

    viewModel {
        SeriesViewModel(
            getAllMoviesUseCase = get(),
            getTrendingMovieUseCase = get(),
            getDetailMovieUseCase = get(),
            saveMovieUseCase = get(),
            verifyExists = get(),
            deleteMovieUseCase = get(),
            mapper = get()
        )
    }

    viewModel {
        DetalhesViewModel(
            getDetailMovieUseCase = get(),
            getSimilarMoviesUseCase = get(),
            saveMovieUseCase = get(),
            verifyExists = get(),
            deleteMovieUseCase = get(),
            mapper = get()
        )
    }

    viewModel {
        PesquisaViewModel(
            searchMovieUseCase = get(),
            mapper = get()
        )
    }

    viewModel {
        FavoritosViewModel(
            getFavMovieUseCase = get(),
            deleteMovieUseCase = get(),
            mapper = get()
        )
    }

    viewModel {
        LoginViewModel(
            autenticaUsuarioUseCase = get(),
            mapper = get()
        )
    }

    viewModel {
        CadastroViewModel(
            mapper = get(),
            cadastraUsuarioUseCase = get()
        )
    }
    // Endregion
}
