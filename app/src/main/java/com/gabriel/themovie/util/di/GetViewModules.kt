package com.gabriel.themovie.util.di

import com.gabriel.themovie.genero.mapper.GeneroViewMapper
import com.gabriel.themovie.movie.mapper.MovieViewMapper
import com.gabriel.themovie.ui.view.detalhes.DetalhesViewModel
import com.gabriel.themovie.ui.view.filmes.FilmesViewModel
import com.gabriel.themovie.ui.view.pesquisa.PesquisaViewModel
import com.gabriel.themovie.ui.view.series.SeriesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

fun getViewModules() = module {
    // Region Mappers
    factory { GeneroViewMapper() }
    factory { MovieViewMapper(mapper = get()) }
    // Endregion

    // Region ViewModels
    viewModel {
        FilmesViewModel(
            getAllMoviesUseCase = get(),
            getTrendingMovieUseCase = get(),
            mapper = get()
        )
    }

    viewModel {
        SeriesViewModel(
            getAllMoviesUseCase = get(),
            getTrendingMovieUseCase = get(),
            mapper = get()
        )
    }

    viewModel {
        DetalhesViewModel(
            getDetailMovieUseCase = get(),
            getSimilarMoviesUseCase = get(),
            mapper = get()
        )
    }

    viewModel {
        PesquisaViewModel(
            searchMovieUseCase = get(),
            mapper = get()
        )
    }
    // Endregion
}
