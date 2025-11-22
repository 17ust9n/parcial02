package com.example.parcial02.data.local.entities

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "medicos",
    indices = [
        Index(value = ["especialidad"]) // Índice para búsquedas más rápidas por especialidad
    ]
)
data class MedicoEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,          // ID autogenerado por Room
    val nombre: String,       // Nombre del médico
    val apellido: String,     // Apellido del médico
    val especialidad: String  // Especialidad médica
)
