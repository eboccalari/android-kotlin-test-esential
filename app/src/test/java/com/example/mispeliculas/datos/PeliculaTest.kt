package com.example.mispeliculas.datos

import android.content.res.Resources
import com.example.mispeliculas.R
import org.junit.Assert.*

import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class PeliculaTest {

    @Mock
    private lateinit var resourcesStub: Resources

    /**
     * Mockito Stub
     */
    @Test
    fun getGenerosString_devuelveStringCorrecto() {
        val pelicula = Pelicula("La mosca",
        "asldf",
        5.0,
        listOf(1, 2),
        "añsldkfjalskjf")
        pelicula.generos = listOf("terror", "drama", "comedia")
        `when`(resourcesStub.getString(R.string.lista_de_generos))
            .thenReturn("Géneros: ")
        val generos = pelicula.getGenerosString(resourcesStub)
        assertEquals("Géneros: terror, drama, comedia", generos)
    }
}