package com.example.classnotify.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Insert
import androidx.room.Update
import com.example.classnotify.models.Materia
import kotlinx.coroutines.flow.Flow


@Dao
interface MateriaDatabaseDao  {

    @Query("SELECT * FROM materia")
    fun obtenerMateria(): Flow<List<Materia>>

    @Insert
    suspend fun registrarMateria(materia:Materia)

    @Update
    suspend fun actualizarMateria(materia:Materia)

    @Delete
    suspend fun borrarMateria(materia:Materia)
}