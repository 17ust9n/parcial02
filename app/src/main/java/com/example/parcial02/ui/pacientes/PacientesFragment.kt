package com.example.parcial02.ui.pacientes

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.parcial02.R
import com.example.parcial02.data.local.entities.PacienteEntity

class PacientesFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var btnLeft: ImageButton
    private lateinit var btnRight: ImageButton

    private lateinit var adapter: PacienteAdapter  // ← CAMBIO AQUÍ
    private lateinit var layoutManager: LinearLayoutManager

    private var pacienteSeleccionado: PacienteEntity? = null
    private var ultimoID = 6

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

        adapter = PacienteAdapter { paciente ->  // ← CAMBIO AQUÍ
            pacienteSeleccionado = paciente
        }

        layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter

        // Datos de prueba
        val pacientesPrueba = mutableListOf(
            PacienteEntity(1, "Pedro", "González", "25", "555-1234", "pedro.gonzalez@email.com", "Hipertensión"),
            PacienteEntity(2, "Laura", "Rodríguez", "30", "555-5678", "laura.rodriguez@email.com", "Diabetes tipo 2"),
            PacienteEntity(3, "Miguel", "Fernández", "45", "555-9012", "miguel.fernandez@email.com", "Artritis"),
            PacienteEntity(4, "Sofia", "Torres", "28", "555-3456", "sofia.torres@email.com", "Asma"),
            PacienteEntity(5, "Diego", "Ramírez", "35", "555-7890", "diego.ramirez@email.com", "Migraña crónica")
        )

        adapter.updateData(pacientesPrueba)

        setupScrollButtons()

        view.findViewById<Button>(R.id.btnVolver).setOnClickListener {
            parentFragmentManager.popBackStack()
        }

        val btnAñadir = view.findViewById<Button>(R.id.btnAnadirPaciente)
        val btnModificar = view.findViewById<Button>(R.id.btnModificarPaciente)
        val btnEliminar = view.findViewById<Button>(R.id.btnEliminarPaciente)

        btnAñadir.setOnClickListener { mostrarDialogoAgregar() }

        btnModificar.setOnClickListener {
            if (pacienteSeleccionado == null)
                mostrarMensaje("Selecciona un paciente primero")
            else
                mostrarDialogoModificar(pacienteSeleccionado!!)
        }

        btnEliminar.setOnClickListener {
            if (pacienteSeleccionado == null)
                mostrarMensaje("Selecciona un paciente primero")
            else
                eliminarPaciente(pacienteSeleccionado!!)
        }
    }

    // ✨ AGREGAR PACIENTE
    private fun mostrarDialogoAgregar() {
        val view = layoutInflater.inflate(R.layout.dialog_paciente, null)

        val dialog = AlertDialog.Builder(requireContext())
            .setTitle("Añadir Paciente")
            .setView(view)
            .setPositiveButton("Guardar") { _, _ ->
                val nuevo = PacienteEntity(
                    id = ultimoID++,
                    nombre = view.findViewById<EditText>(R.id.editNombre).text.toString(),
                    apellido = view.findViewById<EditText>(R.id.editApellido).text.toString(),
                    edad = view.findViewById<EditText>(R.id.editEdad).text.toString(),
                    telefono = view.findViewById<EditText>(R.id.editTelefono).text.toString(),
                    email = view.findViewById<EditText>(R.id.editEmail).text.toString(),
                    diagnostico = view.findViewById<EditText>(R.id.editDiagnostico).text.toString()
                )

                adapter.getLista().add(nuevo)
                adapter.notifyDataSetChanged()
                mostrarMensaje("Paciente agregado")
            }
            .setNegativeButton("Cancelar", null)
            .create()

        dialog.show()
    }

    // ✨ MODIFICAR PACIENTE
    private fun mostrarDialogoModificar(paciente: PacienteEntity) {
        val view = layoutInflater.inflate(R.layout.dialog_paciente, null)

        view.findViewById<EditText>(R.id.editNombre).setText(paciente.nombre)
        view.findViewById<EditText>(R.id.editApellido).setText(paciente.apellido)
        view.findViewById<EditText>(R.id.editEdad).setText(paciente.edad)
        view.findViewById<EditText>(R.id.editTelefono).setText(paciente.telefono)
        view.findViewById<EditText>(R.id.editEmail).setText(paciente.email)
        view.findViewById<EditText>(R.id.editDiagnostico).setText(paciente.diagnostico)

        val dialog = AlertDialog.Builder(requireContext())
            .setTitle("Modificar Paciente")
            .setView(view)
            .setPositiveButton("Guardar") { _, _ ->

                paciente.nombre = view.findViewById<EditText>(R.id.editNombre).text.toString()
                paciente.apellido = view.findViewById<EditText>(R.id.editApellido).text.toString()
                paciente.edad = view.findViewById<EditText>(R.id.editEdad).text.toString()
                paciente.telefono = view.findViewById<EditText>(R.id.editTelefono).text.toString()
                paciente.email = view.findViewById<EditText>(R.id.editEmail).text.toString()
                paciente.diagnostico = view.findViewById<EditText>(R.id.editDiagnostico).text.toString()

                adapter.notifyDataSetChanged()
                mostrarMensaje("Paciente modificado")
            }
            .setNegativeButton("Cancelar", null)
            .create()

        dialog.show()
    }

    // ✨ ELIMINAR PACIENTE
    private fun eliminarPaciente(paciente: PacienteEntity) {
        adapter.getLista().remove(paciente)
        adapter.notifyDataSetChanged()
        pacienteSeleccionado = null
        mostrarMensaje("Paciente eliminado")
    }

    private fun mostrarMensaje(msg: String) {
        Toast.makeText(requireContext(), msg, Toast.LENGTH_SHORT).show()
    }

    // Scroll
    private fun setupScrollButtons() {
        val scrollDistance = 400

        btnLeft.setOnClickListener { recyclerView.smoothScrollBy(-scrollDistance, 0) }
        btnRight.setOnClickListener { recyclerView.smoothScrollBy(scrollDistance, 0) }

        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                updateButtonStates()
            }
        })

        recyclerView.post { updateButtonStates() }
    }

    private fun updateButtonStates() {
        val canScrollLeft = recyclerView.canScrollHorizontally(-1)
        btnLeft.isEnabled = canScrollLeft
        btnLeft.alpha = if (canScrollLeft) 1f else 0.3f

        val canScrollRight = recyclerView.canScrollHorizontally(1)
        btnRight.isEnabled = canScrollRight
        btnRight.alpha = if (canScrollRight) 1f else 0.3f
    }
}