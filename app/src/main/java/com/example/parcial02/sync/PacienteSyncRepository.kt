package com.example.parcial02.sync

import com.example.parcial02.data.local.dao.PacienteDao
import com.example.parcial02.data.remote.PacienteRemoteDataSource
import com.example.parcial02.data.remote.mappers.toLocal

class PacienteSyncRepository(
    private val dao: PacienteDao,
    private val remote: PacienteRemoteDataSource
) {

    // Sincroniza todos los pacientes desde Firebase a Room
    suspend fun syncPacientes() {
        val listaRemota = remote.getAllPacientes()

        // Convertir cada PacienteRemote → PacienteEntity
        val entities = listaRemota.map { it.toLocal() }

        // Guardar en Room
        dao.insertAll(entities)
    }
}
