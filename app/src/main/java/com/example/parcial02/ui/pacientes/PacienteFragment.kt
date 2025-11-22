package com.example.parcial02.ui.pacientes

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.parcial02.R
import com.example.parcial02.data.local.entities.PacienteEntity

class PacientesFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private val adapter = PacientesAdapter()

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
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter

        Log.d("PacientesFragment", "onViewCreated ejecutado")

        // Datos de prueba
        val pacientesPrueba = listOf(
            PacienteEntity(id = 1, nombre = "Juan", apellido = "Pérez", edad = 30),
            PacienteEntity(id = 2, nombre = "Ana", apellido = "García", edad = 25),
            PacienteEntity(id = 3, nombre = "Luis", apellido = "Martínez", edad = 40)
        )

        // Mostrar datos en la tabla
        adapter.updateData(pacientesPrueba)
        Log.d("PacientesFragment", "Pacientes cargados: ${pacientesPrueba.size}")
    }
}
