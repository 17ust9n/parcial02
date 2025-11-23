package com.example.parcial02.ui.pacientes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.parcial02.R
import com.example.parcial02.data.local.entities.PacienteEntity

class PacientesAdapter : RecyclerView.Adapter<PacientesAdapter.PacienteViewHolder>() {

    private var pacientes = listOf<PacienteEntity>()

    fun updateData(newPacientes: List<PacienteEntity>) {
        pacientes = newPacientes
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PacienteViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_paciente, parent, false)
        return PacienteViewHolder(view)
    }

    override fun onBindViewHolder(holder: PacienteViewHolder, position: Int) {
        holder.bind(pacientes[position])
    }

    override fun getItemCount() = pacientes.size

    class PacienteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvNombrePaciente: TextView = itemView.findViewById(R.id.tvNombrePaciente)
        private val tvEdad: TextView = itemView.findViewById(R.id.tvEdad)
        private val tvTelefono: TextView = itemView.findViewById(R.id.tvTelefono)
        private val tvEmail: TextView = itemView.findViewById(R.id.tvEmail)
        private val tvDiagnostico: TextView = itemView.findViewById(R.id.tvDiagnostico)

        fun bind(paciente: PacienteEntity) {
            tvNombrePaciente.text = "${paciente.nombre} ${paciente.apellido}"
            tvEdad.text = "${paciente.edad} años"
            tvTelefono.text = paciente.telefono
            tvEmail.text = paciente.email
            tvDiagnostico.text = paciente.diagnostico
        }
    }
}