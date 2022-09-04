package com.gabriel.remote.filme.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class FilmeContainer(
    @SerializedName("results")
    val results: List<FilmeResponse>
) : Serializable
