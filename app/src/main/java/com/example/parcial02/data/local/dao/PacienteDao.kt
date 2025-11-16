package com.example.parcial02.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.parcial02.data.local.entities.PacienteEntity

@Dao
interface PacienteDao {

    @Query("SELECT * FROM pacientes")
    fun getAll(): LiveData<List<PacienteEntity>>

    @Insert
    suspend fun insert(paciente: PacienteEntity)

    @Insert
    suspend fun insertAll(pacientes: List<PacienteEntity>)
}
