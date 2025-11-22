package com.example.parcial02.ui.medicos

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.parcial02.R
import com.example.parcial02.data.local.entities.MedicoEntity

class MedicosFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private val adapter = MedicosAdapter()

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
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter

        Log.d("MedicosFragment", "onViewCreated ejecutado")

        // Datos de prueba
        val medicosPrueba = listOf(
            MedicoEntity(id = 1, nombre = "Dr. Juan", apellido = "Pérez", especialidad = "Cardiología"),
            MedicoEntity(id = 2, nombre = "Dra. Ana", apellido = "García", especialidad = "Pediatría"),
            MedicoEntity(id = 3, nombre = "Dr. Luis", apellido = "Martínez", especialidad = "Dermatología")
        )

        // Mostrar datos en la tabla
        adapter.updateData(medicosPrueba)
        Log.d("MedicosFragment", "Médicos cargados: ${medicosPrueba.size}")
    }
}
