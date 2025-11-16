package com.example.parcial02.data.sync

import com.example.parcial02.data.local.dao.MedicoDao
import com.example.parcial02.data.local.dao.PacienteDao
import com.example.parcial02.data.remote.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SyncRepository(
    private val medicoDao: MedicoDao,
    private val pacienteDao: PacienteDao,
    private val medicoRemote: MedicoRemoteDataSource,
    private val pacienteRemote: PacienteRemoteDataSource
) {

    suspend fun syncAll() = withContext(Dispatchers.IO) {

        // Médicos
        medicoRemote.getAllMedicos { remoteList ->
            medicoDao.insertAll(remoteList.map { it.toLocal() })
        }

        // Pacientes
        pacienteRemote.getAllPacientes { remoteList ->
            pacienteDao.insertAll(remoteList.map { it.toLocal() })
        }
    }
}
