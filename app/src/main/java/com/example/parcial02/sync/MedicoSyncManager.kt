package com.example.parcial02.sync

import com.example.parcial02.data.local.dao.MedicoDao
import com.example.parcial02.data.remote.MedicoRemoteDataSource
import com.example.parcial02.data.remote.toLocal
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MedicoSyncManager(
    private val medicoDao: MedicoDao,
    private val remote: MedicoRemoteDataSource
) {

    // Sincroniza Firebase → Room
    suspend fun sync() = withContext(Dispatchers.IO) {
        val listaRemota = remote.getAllMedicos()
        val listaEntities = listaRemota.map { it.toLocal() }  // <- Aquí estaba el error
        medicoDao.insertAll(listaEntities)
    }
}
