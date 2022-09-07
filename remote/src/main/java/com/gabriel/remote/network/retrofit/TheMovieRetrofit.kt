package com.gabriel.remote.network.retrofit

import com.gabriel.remote.features.filme.service.FilmesService
import com.gabriel.remote.features.serie.service.SeriesService
import com.gabriel.themovie.util.constants.Constants.API_KEY
import com.gabriel.themovie.util.constants.Constants.API_QUERY
import com.gabriel.themovie.util.constants.Constants.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class TheMovieRetrofit {
    fun getRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    fun getFilmesService(retrofit: Retrofit): FilmesService {
        return retrofit.create(FilmesService::class.java)
    }

    fun getSeriesService(retrofit: Retrofit): SeriesService {
        return retrofit.create(SeriesService::class.java)
    }

    fun getOkHttpClient(): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        return OkHttpClient().newBuilder()
            .addInterceptor { chain ->
                val newUrl = chain.request().url
                    .newBuilder()
                    .addQueryParameter(API_QUERY, API_KEY)
                    .build()

                val newResquest = chain.request()
                    .newBuilder()
                    .url(newUrl)
                    .build()

                chain.proceed(newResquest)
            }
            .addInterceptor(loggingInterceptor)
            .build()
    }
}
