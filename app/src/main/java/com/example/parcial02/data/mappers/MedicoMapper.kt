package com.example.parcial02.data.remote.mappers

import com.example.parcial02.data.local.entities.MedicoEntity
import com.example.parcial02.data.remote.models.MedicoRemote

fun MedicoRemote.toLocal() = MedicoEntity(
    id = this.id,   // ID que viene de Firebase
    nombre = this.nombre,
    apellido = this.apellido,
    especialidad = this.especialidad,
    telefono = this.telefono,
    email = this.email,
    horario = this.horario
)
