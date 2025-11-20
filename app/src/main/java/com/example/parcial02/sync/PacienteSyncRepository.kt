package com.example.parcial02.sync

import com.example.parcial02.data.local.dao.PacienteDao
import com.example.parcial02.data.mappers.toEntity
import com.example.parcial02.data.remote.PacienteRemoteDataSource
import com.example.parcial02.data.remote.models.PacienteRemote

class PacienteSyncRepository(
    private val dao: PacienteDao,
    private val remote: PacienteRemoteDataSource
) {

    // Función suspendida para sincronizar pacientes desde Firebase a Room
    suspend fun syncPacientes() {

        // Obtener pacientes desde Firebase (suspend)
        val lista: List<PacienteRemote> = remote.getAllPacientes()

        // Convertir los PacienteRemote a PacienteEntity
        val entities = lista.map { it.toEntity() }

        // Guardar en Room
        dao.insertAll(entities)
    }
}
