package com.example.mispeliculas.repositorio

object ServiceLocator {
    @Volatile
    var peliculasRepositorio: PeliculasRepositorio? = null
    private val lock = Any()

    fun resetearRepositorio() {
        synchronized(lock) {
            peliculasRepositorio = null
        }
    }

    fun obtenerPeliculasRepositorio(): PeliculasRepositorio {
        synchronized(this) {
            return peliculasRepositorio ?: crearPeliculasRepositorio()
        }
    }

    private fun crearPeliculasRepositorio(): PeliculasRepositorio {
        peliculasRepositorio = TheMovieDBPeliculasRepositorio
        return TheMovieDBPeliculasRepositorio
    }
}