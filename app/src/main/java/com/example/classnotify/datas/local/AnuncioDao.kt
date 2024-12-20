package com.example.classnotify.datas.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Delete
import com.example.classnotify.domain.models.Anuncio

@Dao
interface AnuncioDao {
    @Query("SELECT * FROM Anuncio")
    fun getAllAnuncios(): LiveData<List<Anuncio>>

    @Insert
    suspend fun insertAnuncio(anuncio: Anuncio)

    @Delete
    suspend fun deleteAnuncio(anuncio: Anuncio)
}
