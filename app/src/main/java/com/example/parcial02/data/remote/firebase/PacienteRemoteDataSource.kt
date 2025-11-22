package com.example.parcial02.data.remote

import com.example.parcial02.data.remote.models.PacienteRemote

class PacienteRemoteDataSource {

    // Datos de prueba hardcodeados
    suspend fun getAllPacientes(): List<PacienteRemote> {
        return listOf(
            PacienteRemote(1, "Juan", "Pérez", 30),
            PacienteRemote(2, "Ana", "García", 25),
            PacienteRemote(3, "Luis", "Martínez", 40)
        )
    }

    suspend fun addPaciente(paciente: PacienteRemote) {
        // No hace nada, solo para cumplir la interfaz
    }
}
