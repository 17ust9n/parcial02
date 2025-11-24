package com.example.parcial02.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pacientes")
data class PacienteEntity(
    @PrimaryKey(autoGenerate = true)  // Agrega esto
    var id: Int = 0,
    var nombre: String,
    var apellido: String,
    var edad: String,
    var telefono: String,
    var email: String,
    var diagnostico: String
)