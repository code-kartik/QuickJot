package com.example.quickjot.dao

import androidx.lifecycle.LiveData
import androidx.room.*

import com.example.quickjot.entities.notes

@Dao
interface NotesDao {

    @Query("SELECT * FROM notes ORDER BY id ASC")
    fun getAllNotes(): LiveData<List<notes>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertNote(note:notes)

    @Delete
    fun deleteNote(note:notes)
}