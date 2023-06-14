package com.example.quickjot.entities

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Notes")
class notes(@ColumnInfo(name = "title") var title: String, @ColumnInfo(name = "notes_Text") var notesText: String){

    @PrimaryKey(autoGenerate = true) var id:Int = 0

    @ColumnInfo(name = "date_time") var dateTime: String = ""
}