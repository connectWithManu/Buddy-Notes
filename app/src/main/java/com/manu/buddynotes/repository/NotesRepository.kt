package com.manu.buddynotes.repository

import androidx.lifecycle.LiveData
import com.manu.buddynotes.model.Notes
import com.manu.buddynotes.notesdb.NotesDao

class NotesRepository(private val dao: NotesDao) {

    fun getNotes(): LiveData<List<Notes>> = dao.getAllNotes()
    fun getLowNotes(): LiveData<List<Notes>> = dao.getLowNotes()
    fun getMediumNotes(): LiveData<List<Notes>> = dao.getMediumNotes()
    fun getHighNotes(): LiveData<List<Notes>> = dao.getHighNotes()

    suspend fun insertNotes(notes: Notes) {
        dao.insertNotes(notes)
    }

    suspend fun updateNotes(notes: Notes) {
        dao.update(notes)
    }

    suspend fun deleteNotes(id: Int) {
        dao.delete(id)
    }
}