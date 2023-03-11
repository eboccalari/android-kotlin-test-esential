package com.example.mispeliculas

import org.junit.After
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.Ignore

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */

class ExampleUnitTest {

    @Before
    fun ejecutarAntes() {
        println("Ejecutando ejecutarAntes ")
    }

    @After
    fun ejecutarDespues() {
        println("Ejecutando ejecutarDespues ")
    }

    @Test(timeout = 1000L)
    fun debePasarEnUnSegundo() {
        Thread.sleep(2000L)
    }

    @Test
    fun noEsUnTest() {
        println("Ejecutando noEsUnTest ")
    }

    @Ignore
    @Test
    fun addition_isCorrect() {
        println("Ejecutando addition_isCorrect ")
        assertEquals(4, 2 + 2)
    }

    @Test
    fun mas_assert() {
        println("Ejecutando mas_assert ")
        val objeto = null
        assertNull(objeto)
    }

    @Test
    fun sumaDePuntuaciones_esCorrecta() {
        println("Ejecutando sumaDePuntuaciones_esCorrecta ")
        val lista = listOf(1, 25, 9, 7, -1)
        val resultado = sumaDePuntuaciones(lista)
        assertEquals("La suma debe obviar los valores mayores que 10 o menores que 0",
            17,
            resultado)
    }
}

fun sumaDePuntuaciones(lista: List<Int>): Int {
    return lista.sum()
}