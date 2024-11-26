package com.example.classnotify.domain.viewModels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.classnotify.datas.Remote.Api.MateriaRepository
import com.example.classnotify.domain.models.Materia
import com.example.classnotify.datas.local.MateriaDatabaseDao
import com.example.classnotify.ui_presentation.ui.states.MateriaStates
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


class MateriaViewModel (
    private  val dao: MateriaDatabaseDao
): ViewModel() {
    var state by mutableStateOf(MateriaStates())
        private set
    private val _adjuntoBase64 = MutableStateFlow<String?>(null)
    val adjuntoBase64: StateFlow<String?> = _adjuntoBase64.asStateFlow()

    private val repository = MateriaRepository()

    init {
        viewModelScope.launch {
            dao.obtenerMateria().collectLatest {
                state = state.copy(listaMaterias = it)
            }
        }
    }
    fun registrarMateria(materia: Materia) = viewModelScope.launch {
        dao.registrarMateria(materia)
        repository.registrarMateria(materia)
    }

    fun actualizarMateria(materia: Materia) = viewModelScope.launch {
        dao.actualizarMateria(materia)
        repository.actualizarMateria(materia)
    }

    fun borrarMateria(materia: Materia) = viewModelScope.launch {
        dao.borrarMateria(materia)
        repository.borrarMateria(materia)
    }

    fun setAdjunto(base64String: String) {
        _adjuntoBase64.value = base64String.trim()
    }

    fun limpiarDatos() {
        _adjuntoBase64.value = null
    }

    suspend fun getMateriaById(id: Long): Materia? {
        return dao.obtenerMateriaPorId(id)
    }
}