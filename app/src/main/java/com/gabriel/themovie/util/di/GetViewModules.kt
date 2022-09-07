package com.gabriel.themovie.util.di

import com.gabriel.themovie.model.filme.mapper.FilmeViewMapper
import com.gabriel.themovie.model.genero.mapper.GeneroViewMapper
import com.gabriel.themovie.ui.views.filmes.FilmesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

fun getViewModules() = module {
    // Mappers
    factory { GeneroViewMapper() }
    factory { FilmeViewMapper(get()) }

    // Filmes modules
    viewModel { FilmesViewModel(get(), get()) }
}