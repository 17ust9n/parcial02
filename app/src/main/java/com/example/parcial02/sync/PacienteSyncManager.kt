package com.example.parcial02.sync

import com.example.parcial02.data.local.dao.PacienteDao
import com.example.parcial02.data.remote.PacienteRemoteDataSource
import com.example.parcial02.data.remote.mappers.toLocal
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PacienteSyncManager(
    private val pacienteDao: PacienteDao,
    private val remote: PacienteRemoteDataSource
) {

    // Sincroniza todos los pacientes desde Firebase a Room
    suspend fun sync() = withContext(Dispatchers.IO) {
        val listaRemota = remote.getAllPacientes()

        // Convertir cada PacienteRemote → PacienteEntity
        val listaEntities = listaRemota.map { it.toLocal() }

        // Guardar en Room
        pacienteDao.insertAll(listaEntities)
    }
}
