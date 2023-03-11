package com.example.mispeliculas.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.MenuItem
import com.example.mispeliculas.R

class PeliculaDetalleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pelicula_detalle)
        setSupportActionBar(findViewById(R.id.detail_toolbar))

        if (savedInstanceState == null) {
            mostrarFragment()
        }
    }

    private fun mostrarFragment() {
        val fragment = PeliculaDetalleFragment().apply {
            arguments = Bundle().apply {
                putSerializable(PeliculaDetalleFragment.ARG_ITEM,
                    intent.getSerializableExtra(PeliculaDetalleFragment.ARG_ITEM))
            }
        }
        supportFragmentManager.beginTransaction()
            .add(R.id.item_detail_container, fragment)
            .commit()
    }

    override fun onOptionsItemSelected(item: MenuItem) =
            when (item.itemId) {
                android.R.id.home -> {
                    navigateUpTo(Intent(this, PeliculaListaActivity::class.java))
                    true
                }
                else -> super.onOptionsItemSelected(item)
            }
}