package com.example.parcial02.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.parcial02.data.local.entities.PacienteEntity

@Dao
interface PacienteDao {

    @Query("SELECT * FROM pacientes")
    fun getAll(): LiveData<List<PacienteEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(paciente: PacienteEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(pacientes: List<PacienteEntity>)
}
