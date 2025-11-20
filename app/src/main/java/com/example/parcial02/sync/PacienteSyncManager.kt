package com.example.parcial02.sync

import com.example.parcial02.data.local.dao.PacienteDao
import com.example.parcial02.data.mappers.toEntity
import com.example.parcial02.data.remote.PacienteRemoteDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PacienteSyncManager(
    private val pacienteDao: PacienteDao,
    private val remote: PacienteRemoteDataSource
) {

    suspend fun sync() = withContext(Dispatchers.IO) {
        val listaRemota = remote.getAllPacientes()
        val listaEntities = listaRemota.map { it.toEntity() }
        pacienteDao.insertAll(listaEntities)
    }
}
