package com.example.quickjot.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.quickjot.database.notesDatabase
import com.example.quickjot.entities.notes
import com.example.quickjot.repository.notesRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class notesViewModel(application: Application): AndroidViewModel(application) {

    val allNotes:LiveData<List<notes>>
    private val repository:notesRepo

    init {
        val dao = notesDatabase.getDatabase(application).getNotesDao()
        repository = notesRepo(dao)
        allNotes = repository.allNotes
    }

    fun deleteNote(note: notes) = viewModelScope.launch(Dispatchers.IO) {
        repository.delete(note)
    }

    fun addNote(note: notes) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(note)
    }
}