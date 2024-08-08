package com.example.classnotify.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Anuncio")
data class Anuncio(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val titulo: String,
    val descripcion: String
)