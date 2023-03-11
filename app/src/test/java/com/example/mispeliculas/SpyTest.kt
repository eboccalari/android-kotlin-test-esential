package com.example.mispeliculas

import org.junit.Test
import org.mockito.Mockito.*


class SpyTest {

    @Test
    fun giro_soloRuedasDelanterasGiran() {
        val ruedaDelanteraDerecha = spy(Rueda())
        val ruedaDelanteraIzquierda = spy(Rueda())
        val ruedaTraseraDerecha = spy(Rueda())
        val ruedaTraseraIzquierda = spy(Rueda())

        val coche = Coche(ruedaDelanteraIzquierda,
        ruedaTraseraIzquierda,
        ruedaDelanteraDerecha,
        ruedaTraseraDerecha,
        Coche.Traccion.DELANTERA)
        coche.girar(90)
        verify(ruedaDelanteraDerecha, atLeast(1)).girar(90)
        verify(ruedaDelanteraIzquierda).girar(90)
        verify(ruedaTraseraDerecha, never()).girar(90)
        verify(ruedaTraseraIzquierda, never()).girar(90)
    }
}

class Coche(
    val ruedaDelanteraIzquierda: Rueda,
    val ruedaTraseraIzquierda: Rueda,
    val ruedaDelanteraDerecha: Rueda,
    val ruedaTraseraDerecha: Rueda,
    val traccion: Traccion) {

    fun girar(angulo: Int) {
        ruedaDelanteraDerecha.girar(angulo)
        ruedaDelanteraIzquierda.girar(angulo)
    }

    fun acelerar(unidades: Int) {
        when(traccion) {
            Traccion.DELANTERA -> {
                ruedaDelanteraIzquierda.acelerar(unidades)
                ruedaDelanteraDerecha.acelerar(unidades)
            }
            Traccion.TRASERA -> {
                ruedaTraseraIzquierda.acelerar(unidades)
                ruedaTraseraDerecha.acelerar(unidades)
            }
            Traccion.TOTAL -> {
                ruedaDelanteraIzquierda.acelerar(unidades)
                ruedaDelanteraDerecha.acelerar(unidades)
                ruedaTraseraIzquierda.acelerar(unidades)
                ruedaTraseraDerecha.acelerar(unidades)
            }
        }
    }
    enum class Traccion {
        DELANTERA,
        TRASERA,
        TOTAL
    }
}

class Rueda {
    fun girar(angulo: Int) {

    }
    fun acelerar(unidades: Int) {

    }
}