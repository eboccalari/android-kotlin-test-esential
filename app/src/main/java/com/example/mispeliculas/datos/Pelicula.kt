package com.example.mispeliculas.datos

import android.content.res.Resources
import com.bumptech.glide.load.engine.Resource
import com.example.mispeliculas.R
import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.lang.StringBuilder

data class Pelicula(@SerializedName("title") val titulo: String,
                    @SerializedName("poster_path") val posterPath: String?,
                    @SerializedName("vote_average") val puntuacion: Double,
                    @SerializedName("genre_ids")val generoIDs: List<Int>,
                    @SerializedName("overview") val descripcion: String):
    Serializable {

    var imagenUrl: String = ""
    var generos: List<String> = mutableListOf()

    fun getGenerosString(resources: Resources): String {
        val stringBuilder = StringBuilder()
        for (genero in generos) {
            stringBuilder.append(genero)
            stringBuilder.append(", ")
        }
        val listaDeGeneros = stringBuilder.toString().removeSuffix(", ")
        return resources.getString(R.string.lista_de_generos) + listaDeGeneros
    }
}