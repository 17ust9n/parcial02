package com.example.parcial02.sync

import com.example.parcial02.data.local.dao.MedicoDao
import com.example.parcial02.data.remote.MedicoRemoteDataSource
import com.example.parcial02.data.remote.toLocal

class MedicoSyncRepository(
    private val dao: MedicoDao,
    private val remote: MedicoRemoteDataSource
) {

    /**
     * Sincroniza todos los médicos desde Firebase y los guarda en Room.
     */
    suspend fun syncMedicos() {
        // Obtener lista remota
        val listaRemota = remote.getAllMedicos()

        // Mapear a entidades locales
        val entities = listaRemota.map { it.toLocal() }

        // Guardar en Room
        dao.insertAll(entities)
    }
}
