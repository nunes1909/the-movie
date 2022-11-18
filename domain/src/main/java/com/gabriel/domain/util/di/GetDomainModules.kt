package com.gabriel.domain.util.di

import com.gabriel.domain.movie.useCase.*
import com.gabriel.domain.movie.useCaseImpl.*
import com.gabriel.domain.usuario.useCase.AutenticaUsuarioUseCase
import com.gabriel.domain.usuario.useCase.CadastraUsuarioUseCase
import com.gabriel.domain.usuario.useCaseImpl.AutenticaUsuarioUseCaseImpl
import com.gabriel.domain.usuario.useCaseImpl.CadastraUsuarioUseCaseImpl
import org.koin.dsl.module

fun getDomainModules() = module {
    // Region Movies modules
    factory<GetAllMoviesUseCase> {
        GetAllMoviesUseCaseImpl(repository = get())
    }

    factory<GetDetailMovieUseCase> {
        GetDetailMovieUseCaseImpl(repository = get())
    }

    factory<GetSimilarMoviesUseCase> {
        GetSimilarMoviesUseCaseImpl(repository = get())
    }

    factory<GetTrendingMovieUseCase> {
        GetTrendingMovieUseCaseImpl(repository = get())
    }

    factory<SaveMovieUseCase> {
        SaveMovieUseCaseImpl(repository = get())
    }

    factory<GetFavMovieUseCase> {
        GetFavMovieUseCaseImpl(repository = get())
    }

    factory<VerifyExistsMovieUseCase> {
        VerifyExistsMovieUseCaseImpl(repository = get())
    }

    factory<DeleteMovieUseCase> {
        DeleteMovieUseCaseImpl(repository = get())
    }
    // Endregion

    // Multi modules
    factory<SearchMovieUseCase> {
        SearchMovieUseCaseImpl(repository = get())
    }
    // Endregion

    // Region usuario
    factory<AutenticaUsuarioUseCase> {
        AutenticaUsuarioUseCaseImpl(repository = get())
    }
    factory<CadastraUsuarioUseCase> {
        CadastraUsuarioUseCaseImpl(repository = get())
    }
    // Endregion
}
