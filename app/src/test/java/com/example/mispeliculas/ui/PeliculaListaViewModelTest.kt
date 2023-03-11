package com.example.mispeliculas.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.mispeliculas.datos.CriterioDeOrden
import com.example.mispeliculas.datos.Pelicula
import com.example.mispeliculas.repositorio.PeliculasRepositorio
import org.junit.Assert.*
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.contains
import org.hamcrest.Matchers.containsInAnyOrder
import org.junit.Rule
import org.junit.Test

class PeliculaListaViewModelTest {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

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
    fun obtenerGeneros_devuelveElNombreDeLosGenerosDisponibles() {
        val repositorio = object: PeliculasRepositorio {
            override fun obtenerPeliculasActuales(onPeliculasListas: (List<Pelicula>) -> Unit) {
                onPeliculasListas(listOf(pelicula1, pelicula2, pelicula3, pelicula4, pelicula5))
            }

        }
        val viewModel = PeliculaListaViewModel(repositorio)
        val resultado = viewModel.obtenerGeneros()
        assertThat(resultado, containsInAnyOrder(genero1, genero2, genero3, genero4, genero5))
    }

    @Test
    fun filtrarYOrdenar_actualizaPeliculasOrganizadas() {

        val repositorio = object: PeliculasRepositorio {
            override fun obtenerPeliculasActuales(onPeliculasListas: (List<Pelicula>) -> Unit) {
                onPeliculasListas(listOf(pelicula1, pelicula2, pelicula3, pelicula4, pelicula5))
            }

        }
        val viewModel = PeliculaListaViewModel(repositorio)
        viewModel.filtrarYOrdenar(CriterioDeOrden.NOTA, null)
        val value = viewModel.peliculasOrganizadas.value
        assertThat(value, contains(pelicula3, pelicula2, pelicula5, pelicula1, pelicula4))

    }

    @Test
    fun filtrarYOrdenar_filtraYOrdenaPeliculasOrganizadas() {

        val repositorio = object: PeliculasRepositorio {
            override fun obtenerPeliculasActuales(onPeliculasListas: (List<Pelicula>) -> Unit) {
                onPeliculasListas(listOf(pelicula1, pelicula2, pelicula3, pelicula4, pelicula5))
            }

        }
        val viewModel = PeliculaListaViewModel(repositorio)
        viewModel.filtrarYOrdenar(CriterioDeOrden.NOTA, listOf(genero1, genero3))
        val value = viewModel.peliculasOrganizadas.value
        assertThat(value, contains(pelicula3, pelicula2, pelicula5, pelicula1))

    }


}