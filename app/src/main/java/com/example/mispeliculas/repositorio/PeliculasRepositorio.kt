package com.example.mispeliculas.repositorio

import com.example.mispeliculas.datos.Pelicula

interface PeliculasRepositorio {
    fun obtenerPeliculasActuales(onPeliculasListas: (List<Pelicula>) -> Unit)
}