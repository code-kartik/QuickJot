package com.example.quickjot.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.quickjot.R
import com.example.quickjot.databinding.ActivityCreateNoteBinding
import com.example.quickjot.entities.notes
import com.example.quickjot.viewmodel.notesViewModel
import java.text.SimpleDateFormat
import java.util.*

class CreateNoteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCreateNoteBinding
    lateinit var viewModel: notesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_note)
        binding = ActivityCreateNoteBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.imageBack.setOnClickListener {
            onBackPressed()
        }

        viewModel = ViewModelProvider(this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(notesViewModel::class.java)

        binding.dateTime.text = SimpleDateFormat("EEEE, dd MMMM yyyy HH:mm a",Locale.getDefault()).format(Date())

        binding.imageDone.setOnClickListener {
            if(binding.inputTitle.text.toString().trim().isEmpty()) {
                Toast.makeText(this, "Note title cannot be empty", Toast.LENGTH_SHORT).show()
            }
            val noteTitle = binding.inputTitle.text.toString()
            val noteText = binding.note.text.toString()
            if (noteText.isNotEmpty()){
                viewModel.addNote(notes(noteTitle, noteText))
                Toast.makeText(this, "${noteTitle} added!", Toast.LENGTH_SHORT).show()
            }
            onBackPressed()
        }
    }
}