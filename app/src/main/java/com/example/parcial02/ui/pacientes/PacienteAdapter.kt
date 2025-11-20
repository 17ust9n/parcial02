package com.example.parcial02.ui.pacientes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.parcial02.data.local.entities.PacienteEntity

class PacientesAdapter(
    private val lista: List<PacienteEntity>
) : RecyclerView.Adapter<PacientesAdapter.PacienteViewHolder>() {

    class PacienteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nombreText: TextView = itemView.findViewById(android.R.id.text1)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PacienteViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(android.R.layout.simple_list_item_1, parent, false)
        return PacienteViewHolder(view)
    }

    override fun onBindViewHolder(holder: PacienteViewHolder, position: Int) {
        val paciente = lista[position]
        holder.nombreText.text = "${paciente.nombre} ${paciente.apellido} (${paciente.edad} años)"
    }

    override fun getItemCount(): Int = lista.size
}
