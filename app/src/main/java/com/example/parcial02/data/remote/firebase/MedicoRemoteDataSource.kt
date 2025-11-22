package com.example.parcial02.data.remote

import com.example.parcial02.data.remote.models.MedicoRemote
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class MedicoRemoteDataSource(
    private val db: FirebaseFirestore = FirebaseService.db
) {

    // OBTENER MÉDICOS POR ID
    suspend fun getMedicosById(id: Int): List<MedicoRemote> {
        val snapshot = db.collection("medicos")
            .whereEqualTo("id", id)
            .get()
            .await()

        return snapshot.toObjects(MedicoRemote::class.java)
    }

    // OBTENER TODOS (sin filtro)
    suspend fun getAllMedicos(): List<MedicoRemote> {
        val snapshot = db.collection("medicos").get().await()
        return snapshot.toObjects(MedicoRemote::class.java)
    }

    suspend fun addMedico(medico: MedicoRemote) {
        db.collection("medicos").add(medico).await()
    }
}
