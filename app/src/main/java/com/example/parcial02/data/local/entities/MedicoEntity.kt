package com.example.parcial02.data.local.entities

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "medicos",
    indices = [Index(value = ["especialidad"])] // índice opcional para búsquedas por especialidad
)
data class MedicoEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val nombre: String,
    val apellido: String,
    val especialidad: String
)
