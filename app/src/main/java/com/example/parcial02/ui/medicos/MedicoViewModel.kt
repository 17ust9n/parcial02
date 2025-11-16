package com.example.parcial02.ui.medicos

import androidx.lifecycle.*
import com.example.parcial02.data.local.entities.MedicoEntity
import com.example.parcial02.sync.MedicoSyncManager
import com.example.parcial02.data.local.dao.MedicoDao
import kotlinx.coroutines.launch

class MedicoViewModel(
    private val syncManager: MedicoSyncManager,
    private val dao: MedicoDao
) : ViewModel() {

    // LiveData directamente desde Room
    val medicos: LiveData<List<MedicoEntity>> = dao.getAll()

    fun cargarMedicos() {
        viewModelScope.launch {
            syncManager.sync() // sincroniza en background
            // no hace falta asignar nada, LiveData se actualiza solo
        }
    }
}
