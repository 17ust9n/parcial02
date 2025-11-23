package com.example.parcial02.ui.medicos

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
import com.example.parcial02.data.local.entities.MedicoEntity

class MedicosFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var btnLeft: ImageButton
    private lateinit var btnRight: ImageButton
    private val adapter = MedicosAdapter()
    private lateinit var layoutManager: LinearLayoutManager

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_medicos, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.rvMedicos)
        btnLeft = view.findViewById(R.id.btnLeft)
        btnRight = view.findViewById(R.id.btnRight)

        // Configurar RecyclerView
        layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter

        // Datos de prueba
        val medicosPrueba = listOf(
            MedicoEntity(1, "Juan", "Pérez", "Cardiología", "123456789", "juan.perez@hospital.com", "09:00 - 13:00"),
            MedicoEntity(2, "Ana", "García", "Pediatría", "987654321", "ana.garcia@hospital.com", "14:00 - 18:00"),
            MedicoEntity(3, "Luis", "Martínez", "Dermatología", "111222333", "luis.martinez@hospital.com", "10:00 - 16:00"),
            MedicoEntity(4, "María", "López", "Traumatología", "444555666", "maria.lopez@hospital.com", "08:00 - 12:00"),
            MedicoEntity(5, "Carlos", "Sánchez", "Oftalmología", "777888999", "carlos.sanchez@hospital.com", "15:00 - 19:00")
        )

        adapter.updateData(medicosPrueba)

        // Configurar botones de scroll
        setupScrollButtons()

        // Configurar botón volver
        view.findViewById<Button>(R.id.btnVolver)?.setOnClickListener {
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

    // Método alternativo: scroll por items completos
    private fun scrollToNextItem() {
        val firstVisiblePosition = layoutManager.findFirstVisibleItemPosition()
        if (firstVisiblePosition < adapter.itemCount - 1) {
            recyclerView.smoothScrollToPosition(firstVisiblePosition + 1)
        }
    }

    private fun scrollToPreviousItem() {
        val firstVisiblePosition = layoutManager.findFirstVisibleItemPosition()
        if (firstVisiblePosition > 0) {
            recyclerView.smoothScrollToPosition(firstVisiblePosition - 1)
        }
    }
}