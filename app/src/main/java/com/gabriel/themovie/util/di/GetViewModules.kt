package com.gabriel.themovie.util.di

import com.gabriel.themovie.model.filme.mapper.FilmeViewMapper
import com.gabriel.themovie.model.genero.mapper.GeneroViewMapper
import com.gabriel.themovie.model.multiMovie.mapper.MultiMovieMapper
import com.gabriel.themovie.ui.views.detalhes.DetalhesViewModel
import com.gabriel.themovie.ui.views.filmes.FilmesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

fun getViewModules() = module {
    // Mappers
    factory { GeneroViewMapper() }
    factory { FilmeViewMapper(get()) }
    factory { MultiMovieMapper() }

    // Filmes modules
    viewModel { FilmesViewModel(get(), get(), get()) }
    viewModel { DetalhesViewModel(get(), get(), get(), get()) }
}