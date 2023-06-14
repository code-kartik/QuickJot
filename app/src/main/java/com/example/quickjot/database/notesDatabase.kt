package com.example.quickjot.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.quickjot.dao.NotesDao
import com.example.quickjot.entities.notes

@Database(entities = arrayOf(notes::class), version = 2, exportSchema = false)
abstract class notesDatabase: RoomDatabase(){

    abstract fun getNotesDao(): NotesDao

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: notesDatabase? = null

        fun getDatabase(context: Context): notesDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    notesDatabase::class.java,
                    "notes_database"
                ).fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}