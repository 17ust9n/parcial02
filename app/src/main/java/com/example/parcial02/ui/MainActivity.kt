package com.example.parcial02

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.parcial02.ui.login.LoginActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnLogout = findViewById<Button>(R.id.btnLogout)
        val btnMedicos = findViewById<Button>(R.id.btnMedicos)
        val btnPacientes = findViewById<Button>(R.id.btnPacientes)

        // Ir a Médicos
        btnMedicos.setOnClickListener {
            startActivity(Intent(this, com.example.parcial02.ui.medicos.MedicosActivity::class.java))
        }

        // Ir a Pacientes
        btnPacientes.setOnClickListener {
            startActivity(Intent(this, com.example.parcial02.ui.pacientes.PacientesActivity::class.java))
        }

        // Cerrar sesión
        btnLogout.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            finish()
        }

    }
}
