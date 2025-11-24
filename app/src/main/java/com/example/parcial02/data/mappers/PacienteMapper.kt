package com.example.parcial02.data.remote.mappers

import com.example.parcial02.data.local.entities.PacienteEntity
import com.example.parcial02.data.remote.models.PacienteRemote

fun PacienteRemote.toLocal() = PacienteEntity(
    id = this.id,
    nombre = this.nombre,
    apellido = this.apellido,
    edad = this.edad.toString(),  // ← Convierte Int a String
    telefono = this.telefono,
    email = this.email,
    diagnostico = this.diagnostico
)