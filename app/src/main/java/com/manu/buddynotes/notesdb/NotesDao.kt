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

    @Query("select * from BuddyNotes ORDER BY id DESC")
    fun getAllNotes(): LiveData<List<Notes>>

    @Query("select * from BuddyNotes where priority = 1")
    fun getLowNotes(): LiveData<List<Notes>>

    @Query("select * from BuddyNotes where priority = 2")
    fun getMediumNotes(): LiveData<List<Notes>>

    @Query("select * from BuddyNotes where priority = 3")
    fun getHighNotes(): LiveData<List<Notes>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNotes(notes: Notes)

    @Update
    suspend fun update(notes: Notes)

    @Query("DELETE FROM BuddyNotes WHERE id=:id")
    suspend fun delete(id: Int)
}