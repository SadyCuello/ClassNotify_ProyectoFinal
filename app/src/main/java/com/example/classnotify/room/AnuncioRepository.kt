package com.example.classnotify.room

import androidx.lifecycle.LiveData
import com.example.classnotify.data.AnuncioDao
import com.example.classnotify.models.Anuncio

class AnuncioRepository(private val anuncioDao: AnuncioDao) {

    fun getAllAnuncios(): LiveData<List<Anuncio>> {
        return anuncioDao.getAllAnuncios()
    }
    suspend fun insertAnuncio(anuncio: Anuncio) {
        anuncioDao.insertAnuncio(anuncio)
    }
    suspend fun deleteAnuncio(anuncio: Anuncio) {
        anuncioDao.deleteAnuncio(anuncio)
    }
    }