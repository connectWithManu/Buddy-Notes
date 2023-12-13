package com.manu.buddynotes.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.manu.buddynotes.model.Notes

import com.manu.buddynotes.notesdb.NotesDatabase
import com.manu.buddynotes.repository.NotesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NotesViewModel(application: Application): AndroidViewModel(application) {
    private val repository: NotesRepository

    init {
        val dao = NotesDatabase.getInstance(application).notesDao()
        repository = NotesRepository(dao)
    }

    fun getNotes(): LiveData<List<Notes>> = repository.getNotes()
    fun getLowNotes(): LiveData<List<Notes>> = repository.getLowNotes()
    fun getMediumNotes(): LiveData<List<Notes>> = repository.getMediumNotes()
    fun getHighNotes(): LiveData<List<Notes>> = repository.getHighNotes()

    fun insertNotes(notes: Notes) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertNotes(notes)
        }
    }

    fun updateNotes(notes: Notes) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateNotes(notes)
        }

    }

    fun deleteNotes(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteNotes(id)
        }

    }


}