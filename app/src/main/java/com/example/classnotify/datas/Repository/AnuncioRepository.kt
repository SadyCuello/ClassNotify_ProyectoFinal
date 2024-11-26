package com.example.classnotify.datas.Repository

import androidx.lifecycle.LiveData
import com.example.classnotify.datas.local.AnuncioDao
import com.example.classnotify.domain.models.Anuncio

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