package com.example.quickjot.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SearchView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.quickjot.adapter.INotesAdapter
import com.example.quickjot.adapter.NotesAdapter
import com.example.quickjot.databinding.ActivityMainBinding
import com.example.quickjot.entities.notes
import com.example.quickjot.viewmodel.notesViewModel
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity(), INotesAdapter {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel:notesViewModel
    var arrayNotes = ArrayList<notes>()
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

        val adapter: NotesAdapter

        adapter = NotesAdapter(this, this)
        binding.recyclerView.adapter = adapter

        viewModel = ViewModelProvider(this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(notesViewModel::class.java)
        viewModel.allNotes.observe(this, Observer {list->
            list?.let {
                adapter.updateList(it)
            }
        })

        binding.recyclerView.layoutManager = StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL )

        binding.searchBar.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                var tempArr = ArrayList<notes>()

                for(arr in tempArr){
                    if(arr.title.toLowerCase(Locale.getDefault()).contains(p0.toString())){
                        tempArr.add(arr)
                    }
                }

                adapter.updateList(tempArr)
                adapter.notifyDataSetChanged()
                return true
            }
        })
    }

    override fun onItemClicked(note: notes){
        viewModel.deleteNote(note)
        Toast.makeText(this, "${note.title} deleted!", Toast.LENGTH_SHORT).show()
    }
}