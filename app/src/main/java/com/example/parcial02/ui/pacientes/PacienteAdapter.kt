package com.example.parcial02.ui.pacientes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.parcial02.R
import com.example.parcial02.data.local.entities.PacienteEntity

class PacienteAdapter(
    private val onItemClick: (PacienteEntity) -> Unit = {}
) : RecyclerView.Adapter<PacienteAdapter.PacienteViewHolder>() {

    private val lista = mutableListOf<PacienteEntity>()

    class PacienteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nombreText: TextView = itemView.findViewById(R.id.tvNombrePaciente)
        val edadText: TextView = itemView.findViewById(R.id.tvEdad)
        val telefonoText: TextView = itemView.findViewById(R.id.tvTelefono)
        val emailText: TextView = itemView.findViewById(R.id.tvEmail)
        val diagnosticoText: TextView = itemView.findViewById(R.id.tvDiagnostico)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PacienteViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_paciente, parent, false)
        return PacienteViewHolder(view)
    }

    override fun onBindViewHolder(holder: PacienteViewHolder, position: Int) {
        val paciente = lista[position]
        holder.nombreText.text = "${paciente.nombre} ${paciente.apellido}"
        holder.edadText.text = "Edad: ${paciente.edad}"
        holder.telefonoText.text = paciente.telefono
        holder.emailText.text = paciente.email
        holder.diagnosticoText.text = paciente.diagnostico

        // Click listener para seleccionar el paciente
        holder.itemView.setOnClickListener {
            onItemClick(paciente)
        }
    }

    override fun getItemCount(): Int = lista.size

    fun updateData(newLista: List<PacienteEntity>) {
        lista.clear()
        lista.addAll(newLista)
        notifyDataSetChanged()
    }

    // Método para acceder a la lista desde el Fragment
    fun getLista(): MutableList<PacienteEntity> = lista
}