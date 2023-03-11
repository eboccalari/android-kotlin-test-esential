package com.example.mispeliculas.datos


data class Genero(val id: Int, val name: String)

data class Generos(val genres: List<Genero>)