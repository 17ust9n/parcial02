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

    val medicos: LiveData<List<MedicoEntity>> = dao.getAll()

    fun cargarMedicos() {
        viewModelScope.launch {
            repository.syncMedicos()   // <-- YA NO LLEVA PARÁMETROS
        }
    }
}
