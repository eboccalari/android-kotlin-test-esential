package com.example.mispeliculas.ui

import androidx.test.core.app.launchActivity
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.mispeliculas.datos.Pelicula
import com.example.mispeliculas.repositorio.PeliculasRepositorio
import com.example.mispeliculas.repositorio.ServiceLocator
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class PeliculaListaActivityTest {

    private var genero1 = "Drama"
    private val genero2 = "Terror"
    private val genero3 = "Suspense"
    private val genero4 = "Comedia"
    private val genero5 = "Policiaco"

    val pelicula1 = Pelicula("La mosca",
        "asldf",
        5.0,
        listOf(1, 2),
        "añsldkfjalskjf").apply {
        generos = listOf(genero1, genero2)
    }
    val pelicula2 = Pelicula("La roca",
        "asldf",
        5.2,
        listOf(1, 2),
        "añsldkfjalskjf").apply {
        generos = listOf(genero1)
    }
    val pelicula3 = Pelicula("2001: Odisea en el espacio",
        "asldf",
        8.7,
        listOf(1, 2),
        "añsldkfjalskjf").apply {
        generos = listOf(genero3, genero2)
    }
    val pelicula4 = Pelicula("Érase una vez en América",
        "asldf",
        3.1,
        listOf(1, 2),
        "añsldkfjalskjf").apply {
        generos = listOf(genero4)
    }
    val pelicula5 = Pelicula("Espartaco",
        "asldf",
        5.2,
        listOf(1, 2),
        "añsldkfjalskjf").apply {
        generos = listOf(genero1, genero5)
    }

    @Test
    fun ejemploConActivity() {
        val repositorio = object: PeliculasRepositorio {
            override fun obtenerPeliculasActuales(onPeliculasListas: (List<Pelicula>) -> Unit) {
                onPeliculasListas(listOf(pelicula1, pelicula2, pelicula3, pelicula4, pelicula5))
            }

        }
        ServiceLocator.peliculasRepositorio = repositorio
        val escenario = launchActivity<PeliculaListaActivity>()
        ServiceLocator.resetearRepositorio()
        Thread.sleep(10000)
    }
}