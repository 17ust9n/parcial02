package com.example.parcial02.data.remote

import com.example.parcial02.data.remote.models.PacienteRemote
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class PacienteRemoteDataSource(
    private val db: FirebaseFirestore = FirebaseService.db
) {

    suspend fun getAllPacientes(): List<PacienteRemote> {
        val snapshot = db.collection("pacientes").get().await()
        return snapshot.toObjects(PacienteRemote::class.java)
    }

    suspend fun addPaciente(paciente: PacienteRemote) {
        db.collection("pacientes").add(paciente).await()
    }
}
