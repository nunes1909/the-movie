package com.gabriel.remote.features.genero.model

import com.google.gson.annotations.SerializedName

data class GeneroResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String
)
