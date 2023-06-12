package com.example.quickjot.activities

import android.content.Intent
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.provider.ContactsContract.CommonDataKinds.Note
import android.util.Log
import androidx.lifecycle.LiveData
import com.example.quickjot.database.notesDatabase
import com.example.quickjot.databinding.ActivityMainBinding
import com.example.quickjot.entities.notes

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
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
        getNotes()
    }

    fun getNotes(){
        class getNotesTask : AsyncTask<Void, Void, List<ContactsContract.CommonDataKinds.Note>>(){
            override fun doInBackground(vararg p0: Void?): LiveData<List<notes>> {
                return notesDatabase.getDatabase(applicationContext).notesDao.getAllNotes()
            }

            override fun onPostExecute(notes:List<Note>) {
                super.onPostExecute(notes)
                Log.d(notes.toString())
            }
        }
        getNotesTask().execute()
    }
}