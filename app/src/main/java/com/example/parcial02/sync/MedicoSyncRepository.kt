package com.example.parcial02.sync

import com.example.parcial02.data.local.dao.MedicoDao
import com.example.parcial02.data.mappers.toEntity
import com.example.parcial02.data.remote.MedicoRemoteDataSource

class MedicoSyncRepository(
    private val dao: MedicoDao,
    private val remote: MedicoRemoteDataSource
) {

    suspend fun syncMedicos() {
        val lista = remote.getAllMedicos()
        val entities = lista.map { it.toEntity() }
        dao.insertAll(entities)
    }
}
