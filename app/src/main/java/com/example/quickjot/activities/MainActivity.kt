package com.example.quickjot.activities

import android.app.Application
import android.content.Intent
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.provider.ContactsContract.CommonDataKinds.Note
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.quickjot.database.notesDatabase
import com.example.quickjot.databinding.ActivityMainBinding
import com.example.quickjot.entities.notes
import com.example.quickjot.viewmodel.notesViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    lateinit var viewModel:notesViewModel
    val REQUEST_CODE = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.addNewNote.setOnClickListener {
            val intent = Intent(this, CreateNoteActivity::class.java)
            startActivityForResult(intent, REQUEST_CODE)

        }

        viewModel = ViewModelProvider(this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(notesViewModel::class.java)
        viewModel.allNotes.observe(this, Observer {  })
    }
}