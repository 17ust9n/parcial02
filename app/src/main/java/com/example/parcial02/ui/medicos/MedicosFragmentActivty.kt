package com.example.parcial02.ui.medicos

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.parcial02.R

class MedicosFragmentActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_medicos_fragment)

        // Cargar fragment de Médicos si es la primera vez
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, MedicosFragment())
                .commit()
        }
    }
}
