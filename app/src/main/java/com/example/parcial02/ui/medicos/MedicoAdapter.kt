package com.example.parcial02.ui.medicos

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.parcial02.R
import com.example.parcial02.data.local.entities.MedicoEntity

class MedicosAdapter : RecyclerView.Adapter<MedicosAdapter.MedicoViewHolder>() {

    private val lista: MutableList<MedicoEntity> = mutableListOf()

    class MedicoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nombreText: TextView = itemView.findViewById(R.id.txtNombre)
        val especialidadText: TextView = itemView.findViewById(R.id.txtEspecialidad)
        val telefonoText: TextView = itemView.findViewById(R.id.txtTelefono)
        val emailText: TextView = itemView.findViewById(R.id.txtEmail)
        val horarioText: TextView = itemView.findViewById(R.id.txtHorario)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MedicoViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_medico, parent, false)
        return MedicoViewHolder(view)
    }

    override fun onBindViewHolder(holder: MedicoViewHolder, position: Int) {
        val medico = lista[position]
        holder.nombreText.text = "${medico.nombre} ${medico.apellido}"
        holder.especialidadText.text = "Especialidad: ${medico.especialidad}"
        holder.telefonoText.text = "Teléfono: ${medico.telefono}"
        holder.emailText.text = "Email: ${medico.email}"
        holder.horarioText.text = "Horario: ${medico.horario}"
    }

    override fun getItemCount(): Int = lista.size

    fun updateData(newLista: List<MedicoEntity>) {
        lista.clear()
        lista.addAll(newLista)
        notifyDataSetChanged()
    }
}
