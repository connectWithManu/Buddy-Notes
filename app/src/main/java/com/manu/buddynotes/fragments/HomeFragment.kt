package com.manu.buddynotes.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.manu.buddynotes.R
import com.manu.buddynotes.adapter.NotesAdapter
import com.manu.buddynotes.databinding.FragmentHomeBinding
import com.manu.buddynotes.viewmodel.NotesViewModel


class HomeFragment : Fragment() {
    private val binding by lazy { FragmentHomeBinding.inflate(layoutInflater) }
    private lateinit var adapter: NotesAdapter
    private val notesViewModel: NotesViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.fabAdd.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_homeFragment_to_createFragment)
        }

        notesViewModel.getNotes().observe(viewLifecycleOwner) {notesList ->
            binding.recyclerView.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            adapter = NotesAdapter(requireContext(), notesList)
            binding.recyclerView.adapter = adapter

        }

    }
}