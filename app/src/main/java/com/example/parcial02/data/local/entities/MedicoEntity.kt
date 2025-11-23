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
    val id: Int = 0,          // Room genera automáticamente el id
    val nombre: String,
    val apellido: String,
    val especialidad: String,
    val telefono: String,
    val email: String,
    val horario: String
)
