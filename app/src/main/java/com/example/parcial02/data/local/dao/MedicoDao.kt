package com.example.parcial02.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.parcial02.data.local.entities.MedicoEntity

@Dao
interface MedicoDao {

    @Query("SELECT * FROM medicos")
    fun getAll(): LiveData<List<MedicoEntity>>

    @Query("SELECT * FROM medicos WHERE id = :id")
    suspend fun getById(id: Int): List<MedicoEntity>

    @Insert
    suspend fun insert(medico: MedicoEntity)

    @Insert
    suspend fun insertAll(medicos: List<MedicoEntity>)
}
