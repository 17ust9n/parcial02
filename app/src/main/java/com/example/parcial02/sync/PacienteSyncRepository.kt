package com.example.parcial02.sync

import com.example.parcial02.data.local.dao.PacienteDao
import com.example.parcial02.data.mappers.toEntity
import com.example.parcial02.data.remote.PacienteRemoteDataSource

class PacienteSyncRepository(
    private val dao: PacienteDao,
    private val remote: PacienteRemoteDataSource
) {
    suspend fun syncPacientes() {
        val listaRemota = remote.getAllPacientes()
        val entities = listaRemota.map { it.toEntity() }
        dao.insertAll(entities)
    }
}
