package com.manu.buddynotes.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.manu.buddynotes.R
import com.manu.buddynotes.databinding.FragmentCreateBinding
import com.manu.buddynotes.model.Notes
import com.manu.buddynotes.viewmodel.NotesViewModel
import java.util.Calendar


class CreateFragment : Fragment() {
    private val binding by lazy { FragmentCreateBinding.inflate(layoutInflater) }
    private var priority = "1"
    private val noteViewModel:NotesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
           fabCreate.setOnClickListener {
                validateNotes()
            }
            setPriorityClickListener(pGreen, "1")

            pGreen.setOnClickListener {  }
            setPriorityClickListener(pGreen, "1")

            pYellow.setOnClickListener {
                setPriorityClickListener(pYellow, "2")
            }

            pRed.setOnClickListener {
                setPriorityClickListener(pRed, "3")
            }


        }



    }

    private fun validateNotes() {
        binding.apply {
            if (etTitle.text.toString().isEmpty()) {
                etTitle.error = "Empty"
            } else {
                etTitle.error = null
                createNotes()
            }
        }
    }

    private fun setPriorityClickListener(imageView: ImageView, priorityValue: String) {
        imageView.setOnClickListener {
            priority = priorityValue
            imageView.setImageResource(R.drawable.ic_done)

            // Clear other image views
            val otherImageViews = listOf(binding.pRed, binding.pYellow, binding.pGreen) - imageView
            otherImageViews.forEach { it.setImageResource(0) }
        }
    }


    private fun createNotes() {
        binding.apply {
            val title = etTitle.text.toString()
            val subTitle = etSubTitle.text.toString()
            val note = etNotes.text.toString()
            val date = Calendar.getInstance().time.toString()

            val noteData = Notes(null, title, subTitle, note, date, priority)

            noteViewModel.insertNotes(noteData)
            Toast.makeText(requireContext(), "Notes Created", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_createFragment_to_homeFragment)
        }
    }

}