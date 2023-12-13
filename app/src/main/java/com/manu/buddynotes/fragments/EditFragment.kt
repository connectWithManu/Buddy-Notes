package com.manu.buddynotes.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.manu.buddynotes.R
import com.manu.buddynotes.databinding.FragmentEditBinding
import com.manu.buddynotes.model.Notes
import com.manu.buddynotes.viewmodel.NotesViewModel
import java.util.Calendar

class EditFragment : Fragment() {
    private val binding by lazy { FragmentEditBinding.inflate(layoutInflater) }
    private val noteData: EditFragmentArgs by navArgs()
    private val noteViewModel: NotesViewModel by viewModels()
    private var priority = "1"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.etNotes.setText(noteData.data.notes)
        binding.etTitle.setText(noteData.data.title)
        binding.etSubTitle.setText(noteData.data.subTitle)

        setPriorityClickListener(binding.pGreen, "1")
        setPriorityClickListener(binding.pYellow, "2")
        setPriorityClickListener(binding.pRed, "3")

        when(noteData.data.priority) {
            "1" -> {
                binding.pGreen.setImageResource(R.drawable.green)
            }
            "2" -> {
                binding.pYellow.setImageResource(R.drawable.yellow)
            }
            "3" -> {
                binding.pRed.setImageResource(R.drawable.red)
            }
        }

        binding.fabUpdate.setOnClickListener {
            updateNotes(it)
        }

    }

    private fun updateNotes(view: View) {
        binding.apply {
            val title = etTitle.text.toString()
            val subTitle = etSubTitle.text.toString()
            val note = etNotes.text.toString()
            val date = Calendar.getInstance().time

            val noteData = Notes(noteData.data.id, title, subTitle, note, date.toString(), priority)

            noteViewModel.insertNotes(noteData)
            Toast.makeText(requireContext(), "Notes Updated", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_editFragment_to_homeFragment)
        }
    }

    private fun setPriorityClickListener(imageView: ImageView, priorityValue: String) {
        imageView.setOnClickListener {
            priority = priorityValue
            imageView.setImageResource(R.drawable.ic_done)

            val otherImageViews = listOf(binding.pRed, binding.pYellow, binding.pGreen) - imageView
            otherImageViews.forEach { it.setImageResource(0) }
        }
    }

}