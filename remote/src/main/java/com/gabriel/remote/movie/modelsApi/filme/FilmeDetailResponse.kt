package com.gabriel.remote.movie.modelsApi.filme

import com.gabriel.remote.genero.model.GeneroResponse
import com.gabriel.remote.video.model.filme.FilmeVideoResponse
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class FilmeDetailResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("overview")
    val description: String? = null,
    @SerializedName("vote_average")
    val nota: Double? = null,
    @SerializedName("poster_path")
    val cartaz: String? = null,
    @SerializedName("backdrop_path")
    val banner: String? = null,
    @SerializedName("genres")
    var generos: List<GeneroResponse>? = null,
    @Expose
    val videos: List<FilmeVideoResponse>? = null,
    @Expose
    val favorito: Boolean? = false
) : Serializable
