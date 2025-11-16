package com.example.parcial02.data.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.parcial02.data.local.dao.MedicoDao
import com.example.parcial02.data.local.dao.PacienteDao
import com.example.parcial02.data.local.entities.MedicoEntity
import com.example.parcial02.data.local.entities.PacienteEntity

@Database(
    entities = [PacienteEntity::class, MedicoEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun pacienteDao(): PacienteDao
    abstract fun medicoDao(): MedicoDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "hospital_db"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
