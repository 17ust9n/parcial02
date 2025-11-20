package com.example.parcial02.ui.medicos

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.parcial02.data.local.entities.MedicoEntity

class MedicosAdapter(
    private val lista: List<MedicoEntity>
) : RecyclerView.Adapter<MedicosAdapter.MedicoViewHolder>() {

    class MedicoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nombreText: TextView = itemView.findViewById(android.R.id.text1)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MedicoViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(android.R.layout.simple_list_item_1, parent, false)
        return MedicoViewHolder(view)
    }

    override fun onBindViewHolder(holder: MedicoViewHolder, position: Int) {
        val medico = lista[position]
        holder.nombreText.text = "${medico.nombre} ${medico.apellido} (${medico.especialidad})"
    }

    override fun getItemCount(): Int = lista.size
}
