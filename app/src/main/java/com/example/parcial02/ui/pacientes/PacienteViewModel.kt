// PacienteViewModel.kt
package com.example.parcial02.ui.pacientes

import androidx.lifecycle.*
import com.example.parcial02.data.local.dao.PacienteDao
import com.example.parcial02.data.local.entities.PacienteEntity
import com.example.parcial02.sync.PacienteSyncRepository
import kotlinx.coroutines.launch

class PacienteViewModel(
    private val repository: PacienteSyncRepository,
    private val dao: PacienteDao
) : ViewModel() {

    val pacientes: LiveData<List<PacienteEntity>> = dao.getAll()

    fun cargarPacientes() {
        viewModelScope.launch {
            repository.syncPacientes()
        }
    }
}
