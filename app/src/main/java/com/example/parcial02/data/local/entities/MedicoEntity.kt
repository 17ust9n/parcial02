package com.example.parcial02.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Index

@Entity(
    tableName = "medicos",
    indices = [Index(value = ["especialidad"])]
)
data class MedicoEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,          // Room genera automáticamente el id
    var nombre: String,
    var apellido: String,
    var especialidad: String,
    var telefono: String,
    var email: String,
    var horario: String
)
