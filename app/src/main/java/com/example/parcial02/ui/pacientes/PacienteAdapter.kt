package com.example.parcial02.ui.pacientes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.parcial02.R
import com.example.parcial02.data.local.entities.PacienteEntity

class PacientesAdapter : RecyclerView.Adapter<PacientesAdapter.PacienteViewHolder>() {

    private val lista: MutableList<PacienteEntity> = mutableListOf()

    class PacienteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nombreText: TextView = itemView.findViewById(R.id.txtNombre)
        val edadText: TextView = itemView.findViewById(R.id.txtEdad)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PacienteViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_paciente, parent, false)
        return PacienteViewHolder(view)
    }

    override fun onBindViewHolder(holder: PacienteViewHolder, position: Int) {
        val paciente = lista[position]
        holder.nombreText.text = "${paciente.nombre} ${paciente.apellido}"
        holder.edadText.text = "Edad: ${paciente.edad} años"
    }

    override fun getItemCount(): Int = lista.size

    fun updateData(newLista: List<PacienteEntity>) {
        lista.clear()
        lista.addAll(newLista)
        notifyDataSetChanged()
    }
}
