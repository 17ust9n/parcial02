package com.example.parcial02.sync

import com.example.parcial02.data.local.dao.MedicoDao
import com.example.parcial02.data.mappers.toEntity
import com.example.parcial02.data.remote.MedicoRemoteDataSource

class MedicoSyncRepository(
    private val dao: MedicoDao,
    private val remote: MedicoRemoteDataSource
) {

    /**
     * Sincroniza todos los médicos desde Firebase y los guarda en Room.
     * Si se pasa un ID, solo se sincroniza ese médico.
     */
    suspend fun syncMedicos(id: Int? = null) {
        // Obtener lista remota
        val listaRemota = remote.getAllMedicos()

        // Filtrar por ID si se indicó
        val listaFiltrada = id?.let { uid ->
            listaRemota.filter { it.id == uid }
        } ?: listaRemota

        // Mapear a entidades locales
        val entities = listaFiltrada.map { it.toEntity() }

        // Guardar en Room
        dao.insertAll(entities)
    }
}
