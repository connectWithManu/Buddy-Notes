package com.manu.buddynotes.notesdb

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.manu.buddynotes.model.Notes

@Database(entities = [Notes::class], version = 1, exportSchema = false)
abstract class NotesDb: RoomDatabase() {
    abstract fun getNotesDao(): NotesDao

    companion object {

        private var INSTANCE: NotesDatabase? = null

        fun getInstance(context: Context): NotesDatabase {
            return INSTANCE ?: synchronized(this) {
                val roomDb = Room.databaseBuilder(
                    context.applicationContext,
                    NotesDatabase::class.java,
                    "Notes"
                ).build()
                INSTANCE = roomDb
                roomDb
            }
        }
    }


}