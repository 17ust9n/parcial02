package com.example.parcial02.data.remote

import com.google.firebase.firestore.FirebaseFirestore

object FirebaseService {
    val db: FirebaseFirestore by lazy { FirebaseFirestore.getInstance() }
}
