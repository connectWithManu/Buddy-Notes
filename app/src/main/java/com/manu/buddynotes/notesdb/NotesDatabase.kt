package com.manu.buddynotes.notesdb

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.manu.buddynotes.model.Notes

@Database(entities = [Notes::class], version = 1, exportSchema = false)
abstract class NotesDatabase: RoomDatabase() {
    abstract fun notesDao(): NotesDao

    companion object {
        @Volatile
        private var INSTANCE: RoomDatabase? = null

        fun getInstance(context: Context): RoomDatabase {
            val temp = INSTANCE
            if(temp != null) {
                return temp
            }

            synchronized(this) {
                val roomDatabase = Room.databaseBuilder(
                    context,
                    NotesDatabase::class.java,
                    "BuddyNotes"
                ).allowMainThreadQueries()
                    .build()

                INSTANCE = roomDatabase
                return roomDatabase
            }
        }
    }

}