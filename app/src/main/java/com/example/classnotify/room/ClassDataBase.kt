package com.example.classnotify.room

import androidx.room.Database
import  androidx.room.RoomDatabase
import com.example.classnotify.models.Materia

@Database(
    entities = [Materia::class],
    version = 1,
    exportSchema = false
)
abstract class ClassDataBase : RoomDatabase(){
    abstract fun materiaDao(): MateriaDatabaseDao
}