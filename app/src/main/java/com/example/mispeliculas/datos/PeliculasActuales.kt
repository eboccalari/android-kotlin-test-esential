package com.example.mispeliculas.datos

import com.google.gson.annotations.SerializedName

data class PeliculasActuales(
    @SerializedName("results") val peliculas: List<Pelicula>)