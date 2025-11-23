package com.example.parcial02

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import com.example.parcial02.ui.login.LoginActivity
import com.example.parcial02.ui.medicos.MedicosFragment
import com.example.parcial02.ui.pacientes.PacientesFragment

class MainActivity : AppCompatActivity() {

    private lateinit var cardMenu: View
    private lateinit var fragmentContainer: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        cardMenu = findViewById(R.id.cardMenu)
        fragmentContainer = findViewById(R.id.fragment_container)

        val btnLogout = findViewById<Button>(R.id.btnLogout)
        val btnMedicos = findViewById<Button>(R.id.btnMedicos)
        val btnPacientes = findViewById<Button>(R.id.btnPacientes)

        // Ir a Médicos
        btnMedicos.setOnClickListener {
            showFragment(MedicosFragment())
        }

        // Ir a Pacientes
        btnPacientes.setOnClickListener {
            showFragment(PacientesFragment())
        }

        // Cerrar sesión
        btnLogout.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            finish()
        }

        // Listener para el botón de retroceso
        supportFragmentManager.addOnBackStackChangedListener {
            if (supportFragmentManager.backStackEntryCount == 0) {
                showMenu()
            }
        }

        // Manejar el botón atrás con OnBackPressedDispatcher
        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (supportFragmentManager.backStackEntryCount > 0) {
                    supportFragmentManager.popBackStack()
                } else {
                    // Si no hay fragments en el stack, permitir que se cierre la app
                    finish()
                }
            }
        })
    }

    private fun showFragment(fragment: androidx.fragment.app.Fragment) {
        // Ocultar menú
        cardMenu.visibility = View.GONE
        fragmentContainer.visibility = View.VISIBLE

        // Cargar fragment
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .addToBackStack(null)
            .commit()
    }

    private fun showMenu() {
        cardMenu.visibility = View.VISIBLE
        fragmentContainer.visibility = View.GONE
    }
}