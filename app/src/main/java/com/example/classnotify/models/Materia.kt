package com.example.classnotify.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "materia")
class Materia (
  @PrimaryKey(autoGenerate = false)
    val idMateria: String,
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
  val adjunto: ByteArray
)
