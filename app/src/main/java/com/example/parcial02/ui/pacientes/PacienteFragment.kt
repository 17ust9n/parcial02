package com.example.parcial02.ui.pacientes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.parcial02.data.local.database.AppDatabase
import com.example.parcial02.data.remote.PacienteRemoteDataSource
import com.example.parcial02.sync.PacienteSyncRepository

class PacientesFragment : Fragment() {

    private lateinit var viewModel: PacienteViewModel
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // RecyclerView simple sin ViewBinding
        recyclerView = RecyclerView(requireContext())
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        return recyclerView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 1️⃣ Inicializar DB y DAO
        val db = AppDatabase.getInstance(requireContext())
        val dao = db.pacienteDao()

        // 2️⃣ Inicializar RemoteDataSource y Repository
        val remote = PacienteRemoteDataSource()
        val repository = PacienteSyncRepository(dao, remote)

        // 3️⃣ Crear ViewModelFactory y ViewModel
        val factory = PacienteViewModelFactory(repository, dao)
        viewModel = ViewModelProvider(this, factory)
            .get(PacienteViewModel::class.java)

        // 4️⃣ Observar LiveData y asignar Adapter
        viewModel.pacientes.observe(viewLifecycleOwner) { lista ->
            recyclerView.adapter = PacientesAdapter(lista)
        }

        // 5️⃣ Sincronizar datos
        viewModel.cargarPacientes()
    }
}
