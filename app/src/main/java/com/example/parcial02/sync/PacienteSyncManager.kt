package com.example.parcial02.sync

import com.example.parcial02.data.local.dao.PacienteDao
import com.example.parcial02.data.local.entities.PacienteEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PacienteSyncManager(
    private val pacienteDao: PacienteDao
) {

    /**
     * Función suspendida que sincroniza los pacientes.
     * Por ahora puede quedarse vacía si no hay API remota,
     * pero está lista para futura lógica de sincronización.
     */
    suspend fun sync() = withContext(Dispatchers.IO) {
        // EJEMPLO: sincronización con API (opcional)
        // val pacientesDesdeApi: List<PacienteEntity> = api.getPacientes()
        // pacienteDao.insertAll(pacientesDesdeApi)

        // Por ahora solo dejamos un placeholder para que compile
    }
}
