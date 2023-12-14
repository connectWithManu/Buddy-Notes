package com.manu.buddynotes.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.widget.SearchView.OnQueryTextListener
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.manu.buddynotes.R
import com.manu.buddynotes.adapter.NotesAdapter
import com.manu.buddynotes.databinding.DialogDeleteBinding
import com.manu.buddynotes.databinding.DialogExitBinding
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

        notesViewModel.getNotes().observe(viewLifecycleOwner) {notesList ->
            if(notesList.isEmpty()) {
                binding.tvStatus.visibility = View.VISIBLE
                binding.recyclerView.visibility = View.GONE
            } else {
                binding.tvStatus.visibility = View.GONE
                binding.recyclerView.visibility = View.VISIBLE
                searchNotesList.addAll(notesList)
                adapter = NotesAdapter(requireContext(), notesList)
                binding.recyclerView.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
                binding.recyclerView.adapter = adapter
            }
        }

        binding.fabAdd.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_createFragment)
        }


        binding.btAll.setOnClickListener {
            notesViewModel.getNotes().observe(viewLifecycleOwner) {notesList ->
                if(notesList.isEmpty()) {
                    binding.tvStatus.visibility = View.VISIBLE
                    binding.recyclerView.visibility = View.GONE
                } else {
                    binding.tvStatus.visibility = View.GONE
                    binding.recyclerView.visibility = View.VISIBLE
                    adapter = NotesAdapter(requireContext(), notesList)
                    binding.recyclerView.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
                    binding.recyclerView.adapter = adapter
                }
            }
        }

        binding.btLow.setOnClickListener {
            notesViewModel.getLowNotes().observe(viewLifecycleOwner) {notesList ->
                if(notesList.isEmpty()) {
                    binding.tvStatus.visibility = View.VISIBLE
                    binding.recyclerView.visibility = View.GONE
                } else {
                    binding.tvStatus.visibility = View.GONE
                    binding.recyclerView.visibility = View.VISIBLE
                    adapter = NotesAdapter(requireContext(), notesList)
                    binding.recyclerView.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
                    binding.recyclerView.adapter = adapter
                }
            }
        }

        binding.btMedium.setOnClickListener {
            notesViewModel.getMediumNotes().observe(viewLifecycleOwner) {notesList ->
                if(notesList.isEmpty()) {
                    binding.tvStatus.visibility = View.VISIBLE
                    binding.recyclerView.visibility = View.GONE
                } else {
                    binding.tvStatus.visibility = View.GONE
                    binding.recyclerView.visibility = View.VISIBLE
                    adapter = NotesAdapter(requireContext(), notesList)
                    binding.recyclerView.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
                    binding.recyclerView.adapter = adapter
                }
            }
        }

        binding.btHigh.setOnClickListener {
            notesViewModel.getHighNotes().observe(viewLifecycleOwner) {notesList ->
                if(notesList.isEmpty()) {
                    binding.tvStatus.visibility = View.VISIBLE
                    binding.recyclerView.visibility = View.GONE
                } else {
                    binding.tvStatus.visibility = View.GONE
                    binding.recyclerView.visibility = View.VISIBLE
                    adapter = NotesAdapter(requireContext(), notesList)
                    binding.recyclerView.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
                    binding.recyclerView.adapter = adapter
                }
            }
        }





        binding.searchView.setOnQueryTextListener(object : OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                binding.searchView.queryHint = "Search here.."
                filteredList(newText)
                return true
            }

        })

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                showExitDialog()
            }

            })


    }

    private fun showExitDialog() {
        val bs = BottomSheetDialog(requireContext())
        val bsl = DialogExitBinding.inflate(layoutInflater)
        bs.setContentView(bsl.root)
        bs.setCanceledOnTouchOutside(true)
        bsl.btExit.setOnClickListener {
            bs.dismiss()
            requireActivity().finish()
        }
        bsl.btCancel.setOnClickListener {
            bs.dismiss()
        }
        bs.show()
    }


    private fun filteredList(newText: String?) {
        val queryText = newText?.lowercase()
        val filteredList = ArrayList<Notes>()
        if(searchNotesList.isEmpty()) {
            Toast.makeText(requireContext(), "Note list is Empty", Toast.LENGTH_SHORT).show()
        } else {
            for(notes in searchNotesList) {
                if(notes.title.lowercase().contains(queryText!!) || notes.subTitle.lowercase().contains(queryText)) {
                    filteredList.add(notes)
                }
            }
            adapter.filteredNotes(filteredList)
        }

    }
}