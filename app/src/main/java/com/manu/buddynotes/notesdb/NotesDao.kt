package com.manu.buddynotes.notesdb

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.manu.buddynotes.model.Notes

@Dao
interface NotesDao {

    @Query("SELECT * FROM BuddyNotes")
    fun getAllNotes(): LiveData<List<Notes>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNotes(notes: Notes)

    @Update
    fun update(notes: Notes)

    @Query("DELETE FROM BuddyNotes WHERE id=:id")
    fun delete(id: Int)
}