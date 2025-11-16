package com.example.parcial02.sync

import com.example.parcial02.data.local.dao.MedicoDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MedicoSyncManager(
    private val medicoDao: MedicoDao
) {

    // Esta función puede sincronizar datos con un servidor.
    // Por ahora la dejamos básica.
    suspend fun sync() = withContext(Dispatchers.IO) {
        // Si no usás API remota, esto puede quedar vacío.
        // Ejemplo: podrías limpiar, insertar, actualizar, etc.
    }
}
