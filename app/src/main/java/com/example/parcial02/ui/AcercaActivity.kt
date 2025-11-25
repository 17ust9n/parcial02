package com.example.parcial02

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class AcercaActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_acerca) // Tu XML que creamos

        // Botón para volver a MainActivity
        val btnVolver = findViewById<Button>(R.id.btnVolver)
        btnVolver.setOnClickListener {
            finish() // Cierra esta activity y vuelve a la anterior
        }
    }
}
