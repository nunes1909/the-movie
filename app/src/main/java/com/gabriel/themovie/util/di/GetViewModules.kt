package com.gabriel.themovie.util.di

import com.gabriel.themovie.genero.mapper.GeneroViewMapper
import com.gabriel.themovie.movie.mapper.MovieViewMapper
import com.gabriel.themovie.ui.views.detalhes.DetalhesViewModel
import com.gabriel.themovie.ui.views.filmes.FilmesViewModel
import com.gabriel.themovie.ui.views.series.SeriesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

fun getViewModules() = module {
    // Mappers
    factory { GeneroViewMapper() }
    factory { MovieViewMapper() }

    // Filmes modules
    viewModel { FilmesViewModel(get(), get(), get()) }
    viewModel { DetalhesViewModel(get(), get(), get(), get(), get(), get(), get(), get()) }

    // Series modules
    viewModel { SeriesViewModel(get(), get(), get()) }
}