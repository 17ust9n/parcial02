package com.example.parcial02.ui.medicos

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.parcial02.MainActivity
import com.example.parcial02.R

class MedicosActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_medicos)

        // Botón Volver
        val btnVolver = findViewById<Button>(R.id.btnVolver)
        btnVolver.setOnClickListener {
            // Solo cerramos esta actividad para volver al Main
            finish()
        }
    }
}
