package com.example.parcial02.ui.medicos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.parcial02.data.local.database.AppDatabase
import com.example.parcial02.data.remote.firebase.MedicoRemoteDataSource
import com.example.parcial02.databinding.FragmentMedicosBinding
import com.example.parcial02.sync.MedicoSyncManager

class MedicosFragment : Fragment() {

    private lateinit var binding: FragmentMedicosBinding
    private lateinit var viewModel: MedicoViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMedicosBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // DB + DAO
        val db = AppDatabase.getInstance(requireContext())
        val dao = db.medicoDao()

        // Remote + Sync
        val remote = MedicoRemoteDataSource()
        val sync = MedicoSyncManager(dao, remote)

        // Factory
        val factory = MedicoViewModelFactory(sync, dao)

        // ViewModel
        viewModel = ViewModelProvider(this, factory).get(MedicoViewModel::class.java)

        // RecyclerView config
        binding.recyclerMedicos.layoutManager = LinearLayoutManager(requireContext())

        // Cargar datos
        viewModel.cargarMedicos()

        // Observer
        viewModel.medicos.observe(viewLifecycleOwner) { lista ->
            binding.recyclerMedicos.adapter = MedicosAdapter(lista)
        }
    }
}

