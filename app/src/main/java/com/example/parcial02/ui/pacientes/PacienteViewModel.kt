package com.example.parcial02.ui.pacientes

import androidx.lifecycle.*
import com.example.parcial02.data.local.entities.PacienteEntity
import com.example.parcial02.sync.PacienteSyncManager
import com.example.parcial02.data.local.dao.PacienteDao
import kotlinx.coroutines.launch

class PacienteViewModel(
    private val syncManager: PacienteSyncManager,
    private val dao: PacienteDao
) : ViewModel() {

    // LiveData directamente desde Room
    val pacientes: LiveData<List<PacienteEntity>> = dao.getAll()

    fun cargarPacientes() {
        viewModelScope.launch {
            syncManager.sync() // sincroniza en background
            // no hace falta asignar nada, LiveData se actualiza solo
        }
    }
}
