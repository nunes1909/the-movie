package com.gabriel.themovie.util.di

import com.gabriel.domain.util.di.getDomainModules
import org.koin.core.module.Module

object GetTheMovieModules {

    fun getTheMovieModules(): List<Module> {
        return listOf(
            getViewModules(),
            getDomainModules(),
            getDataModules(),
            getRemoteModules(),
            getCacheModules()
        )
    }
}