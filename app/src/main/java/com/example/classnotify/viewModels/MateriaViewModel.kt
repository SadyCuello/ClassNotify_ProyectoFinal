package com.example.classnotify.viewModels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.classnotify.models.Materia
import com.example.classnotify.room.MateriaDatabaseDao
import com.example.classnotify.states.MateriaStates
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MateriaViewModel (
    private  val dao: MateriaDatabaseDao
): ViewModel() {
    var state by mutableStateOf(MateriaStates())
        private set

    init{
        viewModelScope.launch {
            dao.obtenerMateria().collectLatest {
                state = state.copy(listaMaterias = it)
            }
        }
    }
    fun registrarMateria(materia: Materia) = viewModelScope.launch {
        dao.registrarMateria(materia)
    }
    fun actualizarMateria(materia: Materia) = viewModelScope.launch {
        dao.actualizarMateria(materia)
    }
    fun borrarMateria(materia: Materia) = viewModelScope.launch {
        dao.borrarMateria(materia)
    }

}