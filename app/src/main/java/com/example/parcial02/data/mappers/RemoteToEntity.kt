package com.example.parcial02.data.mappers

import com.example.parcial02.data.local.entities.MedicoEntity
import com.example.parcial02.data.local.entities.PacienteEntity
import com.example.parcial02.data.remote.models.MedicoRemote
import com.example.parcial02.data.remote.models.PacienteRemote

// MedicoRemote → MedicoEntity
fun MedicoRemote.toEntity() = MedicoEntity(
    nombre = this.nombre,
    apellido = this.apellido,
    especialidad = this.especialidad
)

// PacienteRemote → PacienteEntity
fun PacienteRemote.toEntity() = PacienteEntity(
    nombre = this.nombre,
    apellido = this.apellido,
    edad = this.edad
)
