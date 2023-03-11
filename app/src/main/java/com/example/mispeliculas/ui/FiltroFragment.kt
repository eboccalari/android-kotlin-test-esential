package com.example.mispeliculas.ui

import android.os.Bundle
import android.util.TypedValue
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import com.example.mispeliculas.R
import com.example.mispeliculas.datos.CriterioDeOrden

private const val GENEROS = "generos"

class FiltroFragment : Fragment() {
    private var generos: List<String>? = null
    private lateinit var vistaGeneros: LinearLayout
    private val listaCheckBox = mutableListOf<CheckBox>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            generos = it.getStringArray(GENEROS)?.toList()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_filtro, container, false)
        vistaGeneros = view.findViewById(R.id.lista_generos)
        mostrarGeneros()
        prepararBotones(view)
        return view
    }

    private fun prepararBotones(view: View) {
        view.findViewById<Button>(R.id.boton_aceptar).apply {
            setOnClickListener {
                onAceptar()
            }
        }
        view.findViewById<Button>(R.id.boton_cancelar).apply {
            setOnClickListener {
                onCancelar()
            }
        }
    }

    private fun mostrarGeneros() {
        generos?.let {
            for (genero in it) {
                val checkBox = CheckBox(context).apply {
                    text = genero
                    setTextSize(TypedValue.COMPLEX_UNIT_SP, 20f);
                }
                listaCheckBox.add(checkBox)
                vistaGeneros.addView(checkBox)
            }
        }
    }

    private fun onCancelar() {
        setFragmentResult(RESULTADO_KEY, bundleOf())
    }

    private fun onAceptar() {
        setFragmentResult(RESULTADO_KEY, bundleOf(
            ORDEN to getOrden(),
            GENEROS_SELECCIONADOS to getGeneros().toTypedArray()
        ))
    }
    private fun getOrden(): CriterioDeOrden {
        val radioButtonNota = view?.findViewById<RadioButton>(R.id.radio_nota)
        if (radioButtonNota?.isChecked == true) return CriterioDeOrden.NOTA
        val radioButtonTitulo = view?.findViewById<RadioButton>(R.id.radio_titulo)
        if (radioButtonTitulo?.isChecked == true) return CriterioDeOrden.TITULO
        return CriterioDeOrden.NINGUNO
    }
    private fun getGeneros(): List<String> {
        return listaCheckBox.filter { it.isChecked()}.map { it.text.toString() }
    }

    companion object {
        const val RESULTADO_KEY = "filtro resultado key"
        const val ORDEN = "orden"
        const val GENEROS_SELECCIONADOS = "generos seleccionados"

        @JvmStatic
        fun newInstance(generos: List<String>) =
            FiltroFragment().apply {
                arguments = Bundle().apply {
                    putStringArray(GENEROS, generos.toTypedArray())
                }
            }
    }

}