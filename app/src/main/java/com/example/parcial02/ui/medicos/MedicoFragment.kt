package com.example.parcial02.ui.medicos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.parcial02.data.local.database.AppDatabase
import com.example.parcial02.data.remote.MedicoRemoteDataSource
import com.example.parcial02.sync.MedicoSyncRepository

class MedicosFragment : Fragment() {

    private lateinit var viewModel: MedicoViewModel
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
        val dao = db.medicoDao()

        // 2️⃣ Inicializar RemoteDataSource y Repository
        val remote = MedicoRemoteDataSource()
        val repository = MedicoSyncRepository(dao, remote)

        // 3️⃣ Crear ViewModelFactory y ViewModel
        val factory = MedicoViewModelFactory(repository, dao)
        viewModel = ViewModelProvider(this, factory)
            .get(MedicoViewModel::class.java)

        // 4️⃣ Observar LiveData y asignar Adapter
        viewModel.medicos.observe(viewLifecycleOwner) { lista ->
            recyclerView.adapter = MedicosAdapter(lista)
        }

        // 5️⃣ Sincronizar datos
        viewModel.cargarMedicos()
    }
}
