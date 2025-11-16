package com.example.parcial02.ui.pacientes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.parcial02.data.local.database.AppDatabase
import com.example.parcial02.data.remote.firebase.PacienteRemoteDataSource
import com.example.parcial02.databinding.FragmentPacientesBinding
import com.example.parcial02.sync.PacienteSyncManager

class PacientesFragment : Fragment() {

    private lateinit var binding: FragmentPacientesBinding
    private lateinit var viewModel: PacienteViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPacientesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Database + DAO
        val db = AppDatabase.getInstance(requireContext())
        val dao = db.pacienteDao()

        // Remote & Sync
        val remote = PacienteRemoteDataSource()
        val sync = PacienteSyncManager(dao, remote)

        // Factory para el ViewModel
        val factory = PacienteViewModelFactory(sync, dao)

        // Instanciar ViewModel con factory
        viewModel = ViewModelProvider(this, factory)[PacienteViewModel::class.java]

        // Configurar RecyclerView
        binding.recyclerPacientes.layoutManager = LinearLayoutManager(requireContext())

        // Cargar datos al iniciar
        viewModel.cargarPacientes()

        // Observar cambios
        viewModel.pacientes.observe(viewLifecycleOwner) { lista ->
            binding.recyclerPacientes.adapter = PacientesAdapter(lista)
        }
    }
}
