package com.example.parcial02.sync

import com.example.parcial02.data.local.dao.MedicoDao
import com.example.parcial02.data.mappers.toEntity
import com.example.parcial02.data.remote.MedicoRemoteDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MedicoSyncManager(
    private val medicoDao: MedicoDao,
    private val remote: MedicoRemoteDataSource
) {

    // Sincroniza Firebase → Room
    suspend fun sync() = withContext(Dispatchers.IO) {
        val listaRemota = remote.getAllMedicos()
        val listaEntities = listaRemota.map { it.toEntity() }
        medicoDao.insertAll(listaEntities)
    }
}
