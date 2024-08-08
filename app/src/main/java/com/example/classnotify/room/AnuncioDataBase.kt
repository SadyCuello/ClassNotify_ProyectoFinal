package com.example.classnotify.room


import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.classnotify.data.AnuncioDao
import com.example.classnotify.models.Anuncio
import com.example.classnotify.models.Materia

@Database(
    entities = [Anuncio::class],
    version = 1,
    exportSchema = false)

abstract class AnuncioDataBase : RoomDatabase() {
    abstract fun anuncioDao(): AnuncioDao
}
