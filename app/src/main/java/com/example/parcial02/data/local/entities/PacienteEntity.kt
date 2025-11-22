package com.example.parcial02.data.local.entities

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "pacientes",
    indices = [Index(value = ["edad"])] // índice opcional para búsquedas por edad
)
data class PacienteEntity(
    @PrimaryKey(autoGenerate = false)  // <-- Usamos el ID de Firebase
    val id: Int,
    val nombre: String,
    val apellido: String,
    val edad: Int
)
