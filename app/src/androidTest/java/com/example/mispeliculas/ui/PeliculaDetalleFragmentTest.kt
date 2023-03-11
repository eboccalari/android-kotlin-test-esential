package com.example.mispeliculas.ui

import androidx.core.os.bundleOf
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.mispeliculas.R
import com.example.mispeliculas.datos.Pelicula
import org.hamcrest.CoreMatchers.containsString
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class PeliculaDetalleFragmentTest {

    private var genero1 = "Drama"
    private val genero2 = "Terror"
    private val descripcion = "Érase una vez una niña..."

    val pelicula1 = Pelicula("La mosca",
        "asldf",
        5.0,
        listOf(1, 2),
        descripcion).apply {
        generos = listOf(genero1, genero2)
        imagenUrl = "https://www.themoviedb.org/t/p/original/eXRQ0lDO4JoA2H61r6QJjVzI3A5.jpg"
    }

    @Test
    fun itemDetail_muestraLaDescripcion() {
        val fragmentArgs = bundleOf(PeliculaDetalleFragment.ARG_ITEM to pelicula1)
        val escenario = launchFragmentInContainer<PeliculaDetalleFragment>(fragmentArgs)
        onView(withId(R.id.item_detail)).check(matches(withText(descripcion)))
    }

    @Test
    fun generosTextView_muestraLosGeneros() {
        val fragmentArgs = bundleOf(PeliculaDetalleFragment.ARG_ITEM to pelicula1)
        val escenario = launchFragmentInContainer<PeliculaDetalleFragment>(fragmentArgs)
        onView(withId(R.id.generos_textview)).check(matches(withText(containsString(genero1))))
        onView(withId(R.id.generos_textview)).check(matches(withText(containsString(genero2))))
    }

    @Test
    fun recreacion_muestraTextosCorrectos() {
        val fragmentArgs = bundleOf(PeliculaDetalleFragment.ARG_ITEM to pelicula1)
        val escenario = launchFragmentInContainer<PeliculaDetalleFragment>(fragmentArgs)
        escenario.recreate()
        onView(withId(R.id.item_detail)).check(matches(withText(descripcion)))
        onView(withId(R.id.generos_textview)).check(matches(withText(containsString(genero1))))
        onView(withId(R.id.generos_textview)).check(matches(withText(containsString(genero2))))
        Thread.sleep(100000)
    }

}