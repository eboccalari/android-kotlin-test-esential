package com.example.mispeliculas.repositorio

import com.example.mispeliculas.api.RetrofitServiceBuilder
import com.example.mispeliculas.api.TheMovieDBService
import com.example.mispeliculas.datos.Generos
import com.example.mispeliculas.datos.Pelicula
import com.example.mispeliculas.datos.PeliculasActuales
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val BASE_URL = "https://api.themoviedb.org/3/"
private const val API_KEY = "ef02e0bfd75b7fbc57e8fc934e769218"
private const val IMAGE_URL_ROOT = "https://image.tmdb.org/t/p/w500/"

object TheMovieDBPeliculasRepositorio : PeliculasRepositorio {
    private val peliculasService: TheMovieDBService = RetrofitServiceBuilder(BASE_URL)
        .buildService(TheMovieDBService::class.java)

    override fun obtenerPeliculasActuales(onPeliculasListas: (List<Pelicula>) -> Unit) {
        val call = peliculasService.obtenerPeliculasActuales(API_KEY, "es", 1, "ES")
        call.enqueue(object : Callback<PeliculasActuales> {
            override fun onResponse(call: Call<PeliculasActuales>, response: Response<PeliculasActuales>) {
                if (response.isSuccessful){
                    val peliculas = response.body()?.peliculas ?: listOf()
                    completarInformacion(peliculas) { peliculasCompleta ->
                        onPeliculasListas(peliculasCompleta)
                    }
                }
            }
            override fun onFailure(call: Call<PeliculasActuales>, t: Throwable) {
                onPeliculasListas(emptyList())
            }
        })
    }

    private fun completarInformacion(peliculas: List<Pelicula>, onPeliculasListas: (List<Pelicula>) -> Unit) {
        val call = peliculasService.obtenerListaDeGeneros(API_KEY, "es")
        call.enqueue(object : Callback<Generos> {
            override fun onResponse(call: Call<Generos>, response: Response<Generos>) {
                if (response.isSuccessful){
                    val generos = response.body()?.genres
                    val mapaDeGeneros = generos?.associateBy({ it.id }, { it.name })
                    mapaDeGeneros?.let { mapa ->
                        peliculas.forEach { pelicula ->
                            pelicula.generos = pelicula.generoIDs.map { mapa[it] ?: "" }
                            pelicula.imagenUrl = IMAGE_URL_ROOT + pelicula.posterPath
                        }
                    }
                    onPeliculasListas(peliculas)
                }
            }
            override fun onFailure(call: Call<Generos>, t: Throwable) {
                onPeliculasListas(peliculas)
            }
        })

    }
}