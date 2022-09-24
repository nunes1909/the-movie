package com.gabriel.themovie.util.application

import android.app.Application
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.preferencesDataStore
import com.gabriel.themovie.util.di.GetTheMovieModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import java.util.prefs.Preferences

class TheMovieApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        // At the top level of your kotlin file:
        startKoin {
            androidContext(this@TheMovieApplication)
            modules(GetTheMovieModules.getTheMovieModules())
        }
    }
}
