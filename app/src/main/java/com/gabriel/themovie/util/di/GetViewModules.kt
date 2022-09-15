package com.gabriel.themovie.util.di

import com.gabriel.themovie.genero.mapper.GeneroViewMapper
import com.gabriel.themovie.movie.mapper.MovieViewMapper
import com.gabriel.themovie.ui.view.detalhes.DetalhesViewModel
import com.gabriel.themovie.ui.view.filmes.FilmesViewModel
import com.gabriel.themovie.ui.view.series.SeriesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

fun getViewModules() = module {
    // Mappers
    factory { GeneroViewMapper() }
    factory { MovieViewMapper(mapper = get()) }

    // Filmes modules
    viewModel {
        FilmesViewModel(
            getAllMoviesUseCase = get(),
            getRecentMovieUseCase = get(),
            mapper = get()
        )
    }

    // Series modules
    viewModel {
        SeriesViewModel(
            getAllMoviesUseCase = get(),
            getRecentMovieUseCase = get(),
            mapper = get()
        )
    }

    // Detalhes modules
    viewModel {
        DetalhesViewModel(
            getDetailMovieUseCase = get(),
            getSimilarMoviesUseCase = get(),
            mapper = get()
        )
    }
}
