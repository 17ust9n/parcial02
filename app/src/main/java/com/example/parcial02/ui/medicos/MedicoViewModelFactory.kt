package com.example.parcial02.ui.medicos

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.parcial02.data.local.dao.MedicoDao
import com.example.parcial02.sync.MedicoSyncManager

class MedicoViewModelFactory(
    private val syncManager: MedicoSyncManager,
    private val dao: MedicoDao
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MedicoViewModel::class.java)) {
            return MedicoViewModel(syncManager, dao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
