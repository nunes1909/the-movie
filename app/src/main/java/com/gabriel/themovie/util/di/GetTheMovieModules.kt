package com.gabriel.themovie.util.di

import com.gabriel.cache.util.di.getCacheModules
import com.gabriel.data.util.di.getDataModules
import com.gabriel.domain.util.di.getDomainModules
import com.gabriel.remote.util.di.getRemoteModules
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
