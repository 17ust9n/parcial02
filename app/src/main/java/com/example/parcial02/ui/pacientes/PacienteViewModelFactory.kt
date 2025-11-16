package com.example.parcial02.ui.pacientes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.parcial02.data.local.dao.PacienteDao
import com.example.parcial02.sync.PacienteSyncManager

class PacienteViewModelFactory(
    private val syncManager: PacienteSyncManager,
    private val dao: PacienteDao
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PacienteViewModel::class.java)) {
            return PacienteViewModel(syncManager, dao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
