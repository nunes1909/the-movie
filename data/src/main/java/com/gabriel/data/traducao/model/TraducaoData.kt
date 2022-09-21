package com.gabriel.data.traducao.model

data class TraducaoData(
    val pais: String,
    val linguagem: String,
    val name: String,
    val filme: TraducaoMovieData
)
