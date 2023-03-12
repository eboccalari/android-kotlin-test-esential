package com.example.mispeliculas

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.launchActivity
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.mispeliculas.datos.Pelicula
import com.example.mispeliculas.repositorio.PeliculasRepositorio
import com.example.mispeliculas.repositorio.ServiceLocator
import com.example.mispeliculas.ui.PeliculaListaActivity
import com.example.mispeliculas.ui.PeliculaListaViewModel
import com.example.mispeliculas.utils.enLaPosicion
import org.hamcrest.CoreMatchers.containsString
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ApplicationEndToEndTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    lateinit var p1: Pelicula
    lateinit var p2: Pelicula
    lateinit var p3: Pelicula
    lateinit var p4: Pelicula
    lateinit var p5: Pelicula
    lateinit var peliculas: List<Pelicula>

    private var g1 = "Drama"
    private val g2 = "Terror"
    private val g3 = "Suspense"
    private val g4 = "Comedia"
    private val g5 = "Policiaco"

    private lateinit var viewModel: PeliculaListaViewModel
    private lateinit var repositorio: PeliculasRepositorio

    @Before
    fun setup(){
        p1 = Pelicula("t8", "--", 4.0, listOf(1, 2), "d1").apply { generos = listOf(g1,g2) }
        p2 = Pelicula("t2", "--", 7.0, listOf(3, 2), "d2").apply { generos = listOf(g3,g2) }
        p3 = Pelicula("t3", "--", 6.0, listOf(4), "d3").apply { generos = listOf(g4) }
        p4 = Pelicula("t4", "--", 4.1, listOf(4,5), "d4").apply { generos = listOf(g1,g5) }
        p5 = Pelicula("t5", "--", 9.3, listOf(3,5), "d5").apply { generos = listOf(g2, g4, g5) }
        peliculas = listOf(p1,p2,p3,p4,p5)
        repositorio = object: PeliculasRepositorio {
            override fun obtenerPeliculasActuales(onPeliculasListas: (List<Pelicula>) -> Unit) {
                onPeliculasListas(peliculas)
            }
        }
        viewModel = PeliculaListaViewModel(repositorio)
    }

    @Test
    fun validarOrdenarYSeleccionarPrimerElementoDelRecyclerView(){
        ServiceLocator.peliculasRepositorio = repositorio
        val escenario = launchActivity<PeliculaListaActivity>()
        onView(withId(R.id.action_settings)).perform(click())
        Thread.sleep(1000)
        onView(withId(R.id.radio_titulo)).perform(click())
        Thread.sleep(1000)
        onView(withId(R.id.boton_aceptar)).perform(click())
        Thread.sleep(1000)
        onView(withId(R.id.item_list)).check(matches(enLaPosicion(0, hasDescendant(withText(p2.titulo)))))
        onView(withId(R.id.item_list)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView(withId(R.id.generos_textview)).check(matches(withText(containsString(g2))))
        onView(withId(R.id.item_detail)).check(matches(withText(p2.descripcion)))
        Thread.sleep(5000)
    }

}