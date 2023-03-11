package com.example.mispeliculas.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mispeliculas.datos.CriterioDeOrden
import com.example.mispeliculas.datos.Pelicula
import com.example.mispeliculas.filtrar
import com.example.mispeliculas.ordenarPorMejorNota
import com.example.mispeliculas.ordenarPorTitulo
import com.example.mispeliculas.repositorio.PeliculasRepositorio

class PeliculaListaViewModel(private val repositorio: PeliculasRepositorio): ViewModel() {

    private var peliculasOriginales: List<Pelicula> = emptyList()
    private val _peliculasOrganizadas = MutableLiveData<List<Pelicula>>()
    val peliculasOrganizadas: LiveData<List<Pelicula>> get() = _peliculasOrganizadas

    init {
        repositorio.obtenerPeliculasActuales { peliculas ->
            peliculasOriginales = peliculas
            _peliculasOrganizadas.value = peliculas
        }
    }

    fun filtrarYOrdenar(criterioDeOrden: CriterioDeOrden, generos: List<String>?) {
        val peliculasFiltradas = if (generos != null &&generos.isNotEmpty()) {
            peliculasOriginales.toMutableList().filtrar(generos)
        } else {
            peliculasOriginales
        }
        val peliculasOrdenadas = when(criterioDeOrden) {
            CriterioDeOrden.TITULO -> peliculasFiltradas.toMutableList().ordenarPorTitulo()
            CriterioDeOrden.NOTA -> peliculasFiltradas.toMutableList().ordenarPorMejorNota()
            CriterioDeOrden.NINGUNO -> peliculasFiltradas
        }
        _peliculasOrganizadas.value = peliculasOrdenadas
    }

    fun obtenerGeneros(): List<String> {
        return peliculasOriginales.map { it.generos }.flatten().distinct()
    }
}

class PeliculaListaViewModelFactory(private val repositorio: PeliculasRepositorio): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return PeliculaListaViewModel(repositorio) as T
    }

}