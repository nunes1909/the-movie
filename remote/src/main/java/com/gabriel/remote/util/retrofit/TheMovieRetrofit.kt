package com.gabriel.remote.util.retrofit

import com.gabriel.remote.filme.service.FilmesService
import com.gabriel.remote.serie.service.SeriesService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class TheMovieRetrofit {
    fun getRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("BASE_URL")
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
                    .addQueryParameter("API", "API_KEY")
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
