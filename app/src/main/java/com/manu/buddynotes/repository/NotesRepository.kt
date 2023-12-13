package com.manu.buddynotes.repository

import androidx.lifecycle.LiveData
import com.manu.buddynotes.model.Notes
import com.manu.buddynotes.notesdb.NotesDao

class NotesRepository(private val dao: NotesDao) {

    fun getNotes(): LiveData<List<Notes>> = dao.getAllNotes()

    fun insertNotes(notes: Notes) {
        dao.insertNotes(notes)
    }

    fun updateNotes(notes: Notes) {
        dao.update(notes)
    }

    fun deleteNotes(id: Int) {
        dao.delete(id)
    }
}