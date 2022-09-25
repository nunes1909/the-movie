package com.gabriel.themovie.util.application

import android.app.Application
import com.gabriel.themovie.util.di.GetTheMovieModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class TheMovieApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@TheMovieApplication)
            modules(GetTheMovieModules.getTheMovieModules())
        }
    }
}
