package com.example.quickjot.repository

import androidx.lifecycle.LiveData
import com.example.quickjot.dao.NotesDao
import com.example.quickjot.entities.notes

class notesRepo(private val notesDao:NotesDao) {
    val allNotes: LiveData<List<notes>> = notesDao.getAllNotes()

    fun insert(note:notes) {
        notesDao.insertNote(note)
    }

    fun delete(note:notes){
        notesDao.deleteNote((note))
    }
}