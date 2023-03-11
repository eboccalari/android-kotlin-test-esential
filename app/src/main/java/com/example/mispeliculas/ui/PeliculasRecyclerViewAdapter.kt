package com.example.mispeliculas.ui

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mispeliculas.R
import com.example.mispeliculas.datos.Pelicula

class PeliculasRecyclerViewAdapter(private val values: List<Pelicula>)
    : RecyclerView.Adapter<PeliculasRecyclerViewAdapter.ViewHolder>() {

    private val onClickListener: View.OnClickListener = View.OnClickListener { v ->
        val item = v.tag as Pelicula
        val intent = Intent(v.context, PeliculaDetalleActivity::class.java).apply {
            putExtra(PeliculaDetalleFragment.ARG_ITEM, item)
        }
        v.context.startActivity(intent)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.pelicula_lista_contenido, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val pelicula = values[position]
        holder.idView.text = pelicula.puntuacion.toString()
        holder.contentView.text = pelicula.titulo

        with(holder.itemView) {
            tag = pelicula
            setOnClickListener(onClickListener)
        }
    }

    override fun getItemCount() = values.size
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val idView: TextView = view.findViewById(R.id.id_text)
        val contentView: TextView = view.findViewById(R.id.content)
    }
}