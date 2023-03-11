package com.example.mispeliculas

import com.example.mispeliculas.datos.Pelicula
import java.text.Normalizer

fun List<Pelicula>.ordenarPorTitulo(): List<Pelicula> {
    return this.sortedBy { it.titulo.sinAcentos() }
}

fun List<Pelicula>.ordenarPorMejorNota(): List<Pelicula> {
    return this.sortedByDescending { it.puntuacion }
}

fun List<Pelicula>.filtrar(generos: List<String>): List<Pelicula> {
    return this.filter { it.generos.any { it in generos } }
}

fun String.sinAcentos(): String {
    val REGEX = "\\p{InCombiningDiacriticalMarks}+".toRegex()
    val temp = Normalizer.normalize(this, Normalizer.Form.NFD)
    return REGEX.replace(temp, "")
}
