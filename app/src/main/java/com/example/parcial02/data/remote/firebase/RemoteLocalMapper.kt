package com.example.parcial02.data.remote

import com.example.parcial02.data.local.entities.*
import com.example.parcial02.data.remote.models.*

fun MedicoRemote.toLocal() = MedicoEntity(
    id = this.id,
    nombre = this.nombre,
    apellido = this.apellido,
    especialidad = this.especialidad
)

fun PacienteRemote.toLocal() = PacienteEntity(
    id = this.id,
    nombre = this.nombre,
    apellido = this.apellido,
    edad = this.edad
)
