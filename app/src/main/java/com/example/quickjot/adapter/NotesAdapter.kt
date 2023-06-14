package com.example.quickjot.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.quickjot.R
import com.example.quickjot.entities.notes
import java.text.SimpleDateFormat
import java.util.*
import javax.xml.transform.ErrorListener
import kotlin.collections.ArrayList

class NotesAdapter(private val context: Context, private val listener: INotesAdapter): RecyclerView.Adapter<NotesAdapter.NoteViewHolder>() {

    private var allNotesInAdapter = ArrayList<notes>()

    class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleText: TextView = itemView.findViewById(R.id.titleText)
        val dateTimeText: TextView = itemView.findViewById(R.id.dateTimeText)
        val deleteButton:ImageView = itemView.findViewById(R.id.deleteButton)

        fun bind(note: notes) {
            titleText.text = note.title
            dateTimeText.text = SimpleDateFormat("EEEE, dd MMMM yyyy HH:mm a",
                Locale.getDefault()).format(Date())
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val viewHolder = NoteViewHolder(LayoutInflater.from(context).inflate(R.layout.note_layout, parent, false))
        viewHolder.deleteButton.setOnClickListener {
            listener.onItemClicked(allNotesInAdapter[viewHolder.adapterPosition])
        }
        return viewHolder
    }

    override fun getItemCount(): Int {
        return allNotesInAdapter.size
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.bind(allNotesInAdapter.get(position))
    }

    fun updateList(newList: List<notes>){
        allNotesInAdapter.clear()
        allNotesInAdapter.addAll(newList)

        notifyDataSetChanged()
    }
}

interface INotesAdapter{
    fun onItemClicked(note: notes)
}