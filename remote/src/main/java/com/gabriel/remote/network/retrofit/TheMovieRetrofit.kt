package com.gabriel.remote.network.retrofit

import com.gabriel.remote.movie.service.filme.FilmesService
import com.gabriel.remote.movie.service.multi.MultiService
import com.gabriel.remote.movie.service.serie.SeriesService
import com.gabriel.remote.movie.service.traducao.TraducaoService
import com.gabriel.remote.movie.service.trending.TrendingService
import com.gabriel.remote.movie.service.video.VideoService
import com.gabriel.remote.util.constants.ConstantsRemote.API_KEY
import com.gabriel.remote.util.constants.ConstantsRemote.API_QUERY
import com.gabriel.remote.util.constants.ConstantsRemote.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class TheMovieRetrofit {
    // Region retrofit
    fun getRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }
    // Endregion

    // Region services
    fun getFilmesService(retrofit: Retrofit): FilmesService {
        return retrofit.create(FilmesService::class.java)
    }

    fun getSeriesService(retrofit: Retrofit): SeriesService {
        return retrofit.create(SeriesService::class.java)
    }

    fun getMultiService(retrofit: Retrofit): MultiService {
        return retrofit.create(MultiService::class.java)
    }

    fun getTrendingService(retrofit: Retrofit): TrendingService {
        return retrofit.create(TrendingService::class.java)
    }

    fun getVideoService(retrofit: Retrofit): VideoService {
        return retrofit.create(VideoService::class.java)
    }

    fun getTraducaoService(retrofit: Retrofit): TraducaoService {
        return retrofit.create(TraducaoService::class.java)
    }
    // Endregion

    // Region okhttp
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
    // Endregion
}
