package com.example.parcial02.ui.medicos

import androidx.lifecycle.*
import com.example.parcial02.data.local.dao.MedicoDao
import com.example.parcial02.data.local.entities.MedicoEntity
import com.example.parcial02.sync.MedicoSyncRepository
import kotlinx.coroutines.launch

class MedicoViewModel(
    private val repository: MedicoSyncRepository,
    private val dao: MedicoDao
) : ViewModel() {

    // LiveData de todos los médicos
    val medicos: LiveData<List<MedicoEntity>> = dao.getAll()

    /**
     * Carga médicos desde Firebase y los guarda en Room.
     * @param id opcional, si se indica, solo sincroniza ese médico
     */
    fun cargarMedicos(id: Int? = null) {
        viewModelScope.launch {
            repository.syncMedicos(id)
        }
    }
}
