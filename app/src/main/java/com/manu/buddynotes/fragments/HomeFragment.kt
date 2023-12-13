package com.manu.buddynotes.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView.OnQueryTextListener
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.manu.buddynotes.R
import com.manu.buddynotes.adapter.NotesAdapter
import com.manu.buddynotes.databinding.FragmentHomeBinding
import com.manu.buddynotes.model.Notes
import com.manu.buddynotes.viewmodel.NotesViewModel


class HomeFragment : Fragment() {
    private val binding by lazy { FragmentHomeBinding.inflate(layoutInflater) }
    private lateinit var adapter: NotesAdapter
    private lateinit var searchNotesList: ArrayList<Notes>
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

        searchNotesList = ArrayList()

        binding.btAll.setOnClickListener {
            notesViewModel.getNotes().observe(viewLifecycleOwner) {notesList ->
                binding.recyclerView.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
                adapter = NotesAdapter(requireContext(), notesList)
                binding.recyclerView.adapter = adapter

            }
        }

        binding.btHigh.setOnClickListener {
            notesViewModel.getHighNotes().observe(viewLifecycleOwner) {notesList ->
                binding.recyclerView.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
                adapter = NotesAdapter(requireContext(), notesList)
                binding.recyclerView.adapter = adapter

            }
        }

        binding.btMedium.setOnClickListener {
            notesViewModel.getMediumNotes().observe(viewLifecycleOwner) {notesList ->
                binding.recyclerView.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
                adapter = NotesAdapter(requireContext(), notesList)
                binding.recyclerView.adapter = adapter

            }
        }

        binding.btLow.setOnClickListener {
            notesViewModel.getLowNotes().observe(viewLifecycleOwner) {notesList ->
                binding.recyclerView.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
                adapter = NotesAdapter(requireContext(), notesList)
                binding.recyclerView.adapter = adapter

            }
        }

        notesViewModel.getNotes().observe(viewLifecycleOwner) {notesList ->
            searchNotesList.addAll(notesList)
            binding.recyclerView.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            adapter = NotesAdapter(requireContext(), notesList)
            binding.recyclerView.adapter = adapter

        }

        binding.searchView.setOnQueryTextListener(object : OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                filteredList(newText)
                return true
            }

        })

    }

    private fun filteredList(newText: String?) {
        val filteredList = ArrayList<Notes>()
        for(notes in searchNotesList) {
            if(notes.title.contains(newText!!) || notes.subTitle.contains(newText)) {
                filteredList.add(notes)
            }

        }
        adapter.filteredNotes(filteredList)
    }
}