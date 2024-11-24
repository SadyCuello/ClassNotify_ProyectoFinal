package com.example.classnotify.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "materia")
data class Materia (
  @PrimaryKey(autoGenerate = true) val idMateria: Long? = null,
    @ColumnInfo("Nombre")
    val nombre: String,
     @ColumnInfo("Profesor")
     val profesor: String,
    @ColumnInfo("Descripcion")
    val descripcion: String,
  @ColumnInfo("Horario")
  val horario: String,
  @ColumnInfo("Aula")
  val aula: String,
  @ColumnInfo("Adjunto")
  val adjunto: String? = null
)
