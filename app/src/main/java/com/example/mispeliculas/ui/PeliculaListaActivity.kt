package com.example.mispeliculas.ui

import android.os.Bundle
import android.view.*
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.RecyclerView
import com.example.mispeliculas.R
import com.example.mispeliculas.datos.CriterioDeOrden
import com.example.mispeliculas.datos.Pelicula
import com.example.mispeliculas.repositorio.ServiceLocator
import com.example.mispeliculas.repositorio.TheMovieDBPeliculasRepositorio


class PeliculaListaActivity : AppCompatActivity() {

    /**
     * Se indica el factory necesario al viewModel provider (viewModels) ya que por defecto
     * el viewModel provider solo puede instanciar viewModels con constructores sin argumentos.
     */
    private val viewModel: PeliculaListaViewModel by viewModels {
        PeliculaListaViewModelFactory(ServiceLocator.obtenerPeliculasRepositorio())
    }

    private var filtroFragment: FiltroFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pelicula_lista)
        prepararToolbar()
        observarPeliculas()
    }

    private fun observarPeliculas() {
        viewModel.peliculasOrganizadas.observe(this) { peliculas ->
            prepararRecyclerView(findViewById(R.id.item_list), peliculas)
        }
    }

    private fun prepararToolbar() {
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        toolbar.title = title
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.action_settings -> onFiltroPulsado()
        }
        return true
    }

    private fun onFiltroPulsado() {
        if (esElFiltroVisible()) {
            esconderFiltro()
        } else {
            viewModel.obtenerGeneros()?.let { generos ->
                mostrarFiltro(generos)
            }
        }
    }

    private fun mostrarFiltro(generos: List<String>) {
        filtroFragment = FiltroFragment.newInstance(generos).also { fragment ->
            setResultadoFiltroListener()
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.frameLayout, fragment)
                addToBackStack(null)
                commit()
            }
        }
    }

    private fun esconderFiltro() {
        if (filtroFragment != null) {
            supportFragmentManager.popBackStack()
            filtroFragment = null
        }
    }

    private fun esElFiltroVisible() = filtroFragment != null

    private fun setResultadoFiltroListener() {
        supportFragmentManager.setFragmentResultListener(
            FiltroFragment.RESULTADO_KEY,
            this) { _, bundle ->
            val orden = bundle.getParcelable(FiltroFragment.ORDEN) as CriterioDeOrden?
            val generosSeleccionados = bundle.getStringArray(FiltroFragment.GENEROS_SELECCIONADOS)?.toList()
            viewModel.filtrarYOrdenar(orden ?: CriterioDeOrden.NINGUNO, generosSeleccionados)
            esconderFiltro()
        }
    }

    private fun prepararRecyclerView(recyclerView: RecyclerView, peliculas: List<Pelicula>) {
        recyclerView.adapter = PeliculasRecyclerViewAdapter(peliculas)
    }
}