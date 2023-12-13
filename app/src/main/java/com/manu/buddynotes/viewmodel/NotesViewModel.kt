package com.manu.buddynotes.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.manu.buddynotes.model.Notes

import com.manu.buddynotes.notesdb.NotesDatabase
import com.manu.buddynotes.repository.NotesRepository

class NotesViewModel(application: Application): AndroidViewModel(application) {
    private val repository: NotesRepository

    init {
        val dao = NotesDatabase.getInstance(application).notesDao()
        repository = NotesRepository(dao)
    }

    fun getNotes(): LiveData<List<Notes>> = repository.getNotes()

    fun insertNotes(notes: Notes) {
        repository.insertNotes(notes)
    }

    fun updateNotes(notes: Notes) {
        repository.updateNotes(notes)
    }

    fun deleteNotes(id: Int) {
        repository.deleteNotes(id)
    }


}