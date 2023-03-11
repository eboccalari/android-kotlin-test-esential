package com.example.mispeliculas.datos

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
enum class CriterioDeOrden: Parcelable {
    TITULO,
    NOTA,
    NINGUNO
}