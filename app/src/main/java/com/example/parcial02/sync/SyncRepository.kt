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
        suspend fun syncAll() = withContext(Dispatchers.IO) {

            // Médicos
            val medicoList = medicoRemote.getAllMedicos()
            medicoDao.insertAll(medicoList.map { it.toLocal() })

            // Pacientes
            val pacienteList = pacienteRemote.getAllPacientes()
            pacienteDao.insertAll(pacienteList.map { it.toLocal() })
        }
    }
}
