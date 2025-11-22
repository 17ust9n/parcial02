package com.example.parcial02.ui.pacientes

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.parcial02.R

class PacientesFragmentActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pacientes_fragment)

        // Cargar fragment de Pacientes si es la primera vez
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, PacientesFragment())
                .commit()
        }
    }
}
