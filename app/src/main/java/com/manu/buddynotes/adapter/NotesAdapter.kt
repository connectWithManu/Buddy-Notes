package com.manu.buddynotes.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.manu.buddynotes.R
import com.manu.buddynotes.databinding.ItemNotesBinding
import com.manu.buddynotes.fragments.HomeFragmentDirections
import com.manu.buddynotes.model.Notes

class NotesAdapter(val context: Context, var noteList: List<Notes>):
RecyclerView.Adapter<NotesAdapter.NotesVH>(){

    fun filteredNotes(newNoteList: List<Notes>) {
        noteList = newNoteList
        notifyDataSetChanged()
    }
    inner class NotesVH(val binding: ItemNotesBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesVH {
        val binding = ItemNotesBinding.inflate(LayoutInflater.from(context), parent, false)
        return NotesVH(binding)
    }

    override fun getItemCount(): Int {
        return noteList.size
    }

    override fun onBindViewHolder(holder: NotesVH, position: Int) {
        val item = noteList[position]
        holder.binding.tvNotes.text = item.notes
        holder.binding.tvTitle.text = item.title
        holder.binding.tvSubTitle.text = item.subTitle
        holder.binding.tvdate.text = item.date

        when(item.priority) {
            "1" -> {
                holder.binding.ivPriority.setImageResource(R.drawable.green)
            }
            "2" -> {
                holder.binding.ivPriority.setImageResource(R.drawable.yellow)
            }
            "3" -> {
                holder.binding.ivPriority.setImageResource(R.drawable.red)
            }
        }

        holder.itemView.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToEditFragment(item)
            Navigation.findNavController(it).navigate(action)
        }

    }
}