package com.example.parcial02.data.remote.models

data class PacienteRemote(
    val id: Int = 0,
    val nombre: String = "",
    val apellido: String = "",
    val edad: Int = 0,
    val telefono: String = "",
    val email: String = "",
    val diagnostico: String = ""
)