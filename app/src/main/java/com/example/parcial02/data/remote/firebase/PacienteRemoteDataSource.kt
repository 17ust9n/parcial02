package com.example.parcial02.data.remote

import com.example.parcial02.data.remote.models.PacienteRemote
import com.google.firebase.firestore.FirebaseFirestore

class PacienteRemoteDataSource(
    private val db: FirebaseFirestore = FirebaseService.db
) {

    fun getAllPacientes(onSuccess: (List<PacienteRemote>) -> Unit) {
        db.collection("pacientes")
            .get()
            .addOnSuccessListener { snap ->
                val list = snap.toObjects(PacienteRemote::class.java)
                onSuccess(list)
            }
    }

    fun addPaciente(paciente: PacienteRemote) {
        db.collection("pacientes").add(paciente)
    }
}
