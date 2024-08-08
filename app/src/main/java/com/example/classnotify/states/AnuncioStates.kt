package com.example.classnotify.states

import androidx.lifecycle.LiveData
import com.example.classnotify.models.Anuncio

data class AnuncioStates(
    val isLoading: Boolean = false,
    val listaAnuncios: List<Anuncio> = emptyList(),
    val errorMessage: String? = null
)