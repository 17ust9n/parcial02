// PacienteViewModelFactory.kt
package com.example.parcial02.ui.pacientes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.parcial02.data.local.dao.PacienteDao
import com.example.parcial02.sync.PacienteSyncRepository

class PacienteViewModelFactory(
    private val repository: PacienteSyncRepository,
    private val dao: PacienteDao
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PacienteViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return PacienteViewModel(repository, dao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

