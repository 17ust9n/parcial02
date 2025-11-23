package com.example.parcial02.ui.pacientes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.parcial02.R
import com.example.parcial02.data.local.entities.PacienteEntity

class PacientesFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var btnLeft: ImageButton
    private lateinit var btnRight: ImageButton
    private val adapter = PacientesAdapter()
    private lateinit var layoutManager: LinearLayoutManager

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_pacientes, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.rvPacientes)
        btnLeft = view.findViewById(R.id.btnLeft)
        btnRight = view.findViewById(R.id.btnRight)

        // Configurar RecyclerView
        layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter

        // Datos de prueba
        val pacientesPrueba = listOf(
            PacienteEntity(1, "María", "González", 25, "555-1111", "maria.gonzalez@email.com", "Diabetes"),
            PacienteEntity(2, "Pedro", "Ramírez", 40, "555-2222", "pedro.ramirez@email.com", "Hipertensión"),
            PacienteEntity(3, "Laura", "Fernández", 32, "555-3333", "laura.fernandez@email.com", "Asma"),
            PacienteEntity(4, "Carlos", "Torres", 55, "555-4444", "carlos.torres@email.com", "Artritis"),
            PacienteEntity(5, "Ana", "Morales", 28, "555-5555", "ana.morales@email.com", "Migraña")
        )

        adapter.updateData(pacientesPrueba)

        // Configurar botones de scroll
        setupScrollButtons()

        // Configurar botón volver (si existe)
        val btnVolver = view.findViewById<Button>(R.id.btnVolver)
        btnVolver?.setOnClickListener {
            parentFragmentManager.popBackStack()
        }
    }

    private fun setupScrollButtons() {
        val scrollDistance = 400 // Distancia de scroll en píxeles

        // Botón izquierda - desplaza hacia la izquierda
        btnLeft.setOnClickListener {
            recyclerView.smoothScrollBy(-scrollDistance, 0)
        }

        // Botón derecha - desplaza hacia la derecha
        btnRight.setOnClickListener {
            recyclerView.smoothScrollBy(scrollDistance, 0)
        }

        // Listener para actualizar el estado de los botones al hacer scroll
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                updateButtonStates()
            }
        })

        // Estado inicial de los botones
        recyclerView.post {
            updateButtonStates()
        }
    }

    private fun updateButtonStates() {
        // Verificar si puede hacer scroll a la izquierda
        val canScrollLeft = recyclerView.canScrollHorizontally(-1)
        btnLeft.isEnabled = canScrollLeft
        btnLeft.alpha = if (canScrollLeft) 1.0f else 0.3f

        // Verificar si puede hacer scroll a la derecha
        val canScrollRight = recyclerView.canScrollHorizontally(1)
        btnRight.isEnabled = canScrollRight
        btnRight.alpha = if (canScrollRight) 1.0f else 0.3f
    }
}