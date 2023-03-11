package com.example.mispeliculas

import com.example.mispeliculas.datos.Pelicula
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.*
import org.hamcrest.core.IsEqual.equalTo
import org.junit.Assert.assertEquals
import org.junit.Test

class ExtensionesTest {
    val pelicula1 = Pelicula("La mosca",
        "asldf",
        5.0,
        listOf(1, 2),
        "añsldkfjalskjf")
    val pelicula2 = Pelicula("La roca",
        "asldf",
        5.2,
        listOf(1, 2),
        "añsldkfjalskjf")
    val pelicula3 = Pelicula("2001: Odisea en el espacio",
        "asldf",
        8.7,
        listOf(1, 2),
        "añsldkfjalskjf")
    val pelicula4 = Pelicula("Érase una vez en América",
        "asldf",
        3.1,
        listOf(1, 2),
        "añsldkfjalskjf")
    val pelicula5 = Pelicula("Espartaco",
        "asldf",
        5.2,
        listOf(1, 2),
        "añsldkfjalskjf")

    @Test
    fun ordenarPorTitulo_ordenaAlfabeticamente() {
        val lista = listOf(pelicula1, pelicula2, pelicula3, pelicula4, pelicula5)
        val listaOrdenada = lista.ordenarPorTitulo()
        val listaEsperada = listOf(pelicula3, pelicula4, pelicula5, pelicula1, pelicula2)
        assertThat(listaEsperada, equalTo(listaOrdenada))
    }

    @Test
    fun ordenarPorNota_ordenaPorNotaDeMayorAMenor() {
        val lista = listOf(pelicula1, pelicula2, pelicula3, pelicula4, pelicula5)
        val listaOrdenada = lista.ordenarPorMejorNota()
        assertThat(listaOrdenada, contains(pelicula3, pelicula2, pelicula5, pelicula1, pelicula4))
    }

    @Test
    fun filtrarGeneros_eliminaPeliculasDeOtrosGeneros() {
        val lista = listOf(pelicula1, pelicula2, pelicula3, pelicula4, pelicula5)
        val generos = listOf("Drama", "Comedia")
        val listaOrdenada = lista.filtrar(generos)
        assertThat(listaOrdenada, not(hasItem(pelicula3)))
    }

    @Test
    fun filtrarGeneros_mantieneLasPeliculasDeLosGenerosDeseados() {
        val lista = listOf(pelicula1, pelicula2, pelicula3, pelicula4, pelicula5)
        val generos = listOf("Drama", "Comedia")
        val listaOrdenada = lista.filtrar(generos)
        assertThat(listaOrdenada, containsInAnyOrder(pelicula1, pelicula2, pelicula4, pelicula5))
    }
}