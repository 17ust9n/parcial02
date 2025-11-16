package com.example.parcial02.data.remote

import com.example.parcial02.data.remote.models.MedicoRemote
import com.google.firebase.firestore.FirebaseFirestore

class MedicoRemoteDataSource(
    private val db: FirebaseFirestore = FirebaseService.db
) {

    fun getAllMedicos(onSuccess: (List<MedicoRemote>) -> Unit) {
        db.collection("medicos")
            .get()
            .addOnSuccessListener { snap ->
                val list = snap.toObjects(MedicoRemote::class.java)
                onSuccess(list)
            }
    }

    fun addMedico(medico: MedicoRemote) {
        db.collection("medicos").add(medico)
    }
}
