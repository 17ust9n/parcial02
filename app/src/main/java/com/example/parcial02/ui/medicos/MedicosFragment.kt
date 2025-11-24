package com.example.parcial02.ui.medicos

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
import com.example.parcial02.data.local.entities.MedicoEntity

class MedicosFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var btnLeft: ImageButton
    private lateinit var btnRight: ImageButton

    private lateinit var adapter: MedicosAdapter
    private lateinit var layoutManager: LinearLayoutManager

    private var medicoSeleccionado: MedicoEntity? = null
    private var ultimoID = 6 // porque tu lista llega hasta el ID 5

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

        adapter = MedicosAdapter { medico ->
            medicoSeleccionado = medico
        }

        layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter

        val medicosPrueba = mutableListOf(
            MedicoEntity(1, "Juan", "Pérez", "Cardiología", "123456789", "juan.perez@hospital.com", "09:00 - 13:00"),
            MedicoEntity(2, "Ana", "García", "Pediatría", "987654321", "ana.garcia@hospital.com", "14:00 - 18:00"),
            MedicoEntity(3, "Luis", "Martínez", "Dermatología", "111222333", "luis.martinez@hospital.com", "10:00 - 16:00"),
            MedicoEntity(4, "María", "López", "Traumatología", "444555666", "maria.lopez@hospital.com", "08:00 - 12:00"),
            MedicoEntity(5, "Carlos", "Sánchez", "Oftalmología", "777888999", "carlos.sanchez@hospital.com", "15:00 - 19:00")
        )

        adapter.updateData(medicosPrueba)

        setupScrollButtons()

        view.findViewById<Button>(R.id.btnVolver).setOnClickListener {
            parentFragmentManager.popBackStack()
        }

        val btnAñadir = view.findViewById<Button>(R.id.btnAnadirMedico)
        val btnModificar = view.findViewById<Button>(R.id.btnModificarMedico)
        val btnEliminar = view.findViewById<Button>(R.id.btnEliminarMedico)

        btnAñadir.setOnClickListener { mostrarDialogoAgregar() }

        btnModificar.setOnClickListener {
            if (medicoSeleccionado == null)
                mostrarMensaje("Selecciona un médico primero")
            else
                mostrarDialogoModificar(medicoSeleccionado!!)
        }

        btnEliminar.setOnClickListener {
            if (medicoSeleccionado == null)
                mostrarMensaje("Selecciona un médico primero")
            else
                eliminarMedico(medicoSeleccionado!!)
        }
    }

    // ✨ AGREGAR MÉDICO
    private fun mostrarDialogoAgregar() {
        val view = layoutInflater.inflate(R.layout.dialog_medico, null)

        val dialog = AlertDialog.Builder(requireContext())
            .setTitle("Añadir Médico")
            .setView(view)
            .setPositiveButton("Guardar") { _, _ ->
                val nuevo = MedicoEntity(
                    id = ultimoID++,
                    nombre = view.findViewById<EditText>(R.id.editNombre).text.toString(),
                    apellido = view.findViewById<EditText>(R.id.editApellido).text.toString(),
                    especialidad = view.findViewById<EditText>(R.id.editEspecialidad).text.toString(),
                    telefono = view.findViewById<EditText>(R.id.editTelefono).text.toString(),
                    email = view.findViewById<EditText>(R.id.editEmail).text.toString(),
                    horario = view.findViewById<EditText>(R.id.editHorario).text.toString()
                )

                adapter.getLista().add(nuevo)
                adapter.notifyDataSetChanged()
            }
            .setNegativeButton("Cancelar", null)
            .create()

        dialog.show()
    }

    // ✨ MODIFICAR MÉDICO
    private fun mostrarDialogoModificar(medico: MedicoEntity) {
        val view = layoutInflater.inflate(R.layout.dialog_medico, null)

        view.findViewById<EditText>(R.id.editNombre).setText(medico.nombre)
        view.findViewById<EditText>(R.id.editApellido).setText(medico.apellido)
        view.findViewById<EditText>(R.id.editEspecialidad).setText(medico.especialidad)
        view.findViewById<EditText>(R.id.editTelefono).setText(medico.telefono)
        view.findViewById<EditText>(R.id.editEmail).setText(medico.email)
        view.findViewById<EditText>(R.id.editHorario).setText(medico.horario)

        val dialog = AlertDialog.Builder(requireContext())
            .setTitle("Modificar Médico")
            .setView(view)
            .setPositiveButton("Guardar") { _, _ ->

                medico.nombre = view.findViewById<EditText>(R.id.editNombre).text.toString()
                medico.apellido = view.findViewById<EditText>(R.id.editApellido).text.toString()
                medico.especialidad = view.findViewById<EditText>(R.id.editEspecialidad).text.toString()
                medico.telefono = view.findViewById<EditText>(R.id.editTelefono).text.toString()
                medico.email = view.findViewById<EditText>(R.id.editEmail).text.toString()
                medico.horario = view.findViewById<EditText>(R.id.editHorario).text.toString()

                adapter.notifyDataSetChanged()
            }
            .setNegativeButton("Cancelar", null)
            .create()

        dialog.show()
    }

    // ✨ ELIMINAR MÉDICO
    private fun eliminarMedico(medico: MedicoEntity) {
        adapter.getLista().remove(medico)
        adapter.notifyDataSetChanged()
        mostrarMensaje("Médico eliminado")
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
