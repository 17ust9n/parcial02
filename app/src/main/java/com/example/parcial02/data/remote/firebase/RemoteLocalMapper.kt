package com.example.parcial02.data.remote

import com.example.parcial02.data.local.entities.MedicoEntity
import com.example.parcial02.data.local.entities.PacienteEntity
import com.example.parcial02.data.remote.models.MedicoRemote
import com.example.parcial02.data.remote.models.PacienteRemote

// Convierte MedicoRemote → MedicoEntity (ID generado automáticamente por Room)
fun MedicoRemote.toLocal() = MedicoEntity(
    nombre = this.nombre,
    apellido = this.apellido,
    especialidad = this.especialidad,
    telefono = this.telefono,
    email = this.email,
    horario = this.horario
)

// Convierte PacienteRemote → PacienteEntity (ID recibido de Firebase)
fun PacienteRemote.toLocal() = PacienteEntity(
    id = this.id,
    nombre = this.nombre,
    apellido = this.apellido,
    edad = this.edad.toString(),  // ← Convierte Int a String
    telefono = this.telefono,
    email = this.email,
    diagnostico = this.diagnostico
)