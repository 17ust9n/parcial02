package com.example.parcial02.data.sync

import com.example.parcial02.data.local.dao.MedicoDao
import com.example.parcial02.data.local.dao.PacienteDao
import com.example.parcial02.data.remote.MedicoRemoteDataSource
import com.example.parcial02.data.remote.PacienteRemoteDataSource
import com.example.parcial02.data.remote.mappers.toLocal
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SyncRepository(
    private val medicoDao: MedicoDao,
    private val pacienteDao: PacienteDao,
    private val medicoRemote: MedicoRemoteDataSource,
    private val pacienteRemote: PacienteRemoteDataSource
) {

    // Sincroniza todos los médicos y pacientes desde Firebase a Room
    suspend fun syncAll() = withContext(Dispatchers.IO) {
        // Médicos
        val medicoList = medicoRemote.getAllMedicos()
        medicoDao.insertAll(medicoList.map { it.toLocal() }) // el mapper usa id de Firebase

        // Pacientes
        val pacienteList = pacienteRemote.getAllPacientes()
        pacienteDao.insertAll(pacienteList.map { it.toLocal() }) // el mapper usa id de Firebase
    }
}
