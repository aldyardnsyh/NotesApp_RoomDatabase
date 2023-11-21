package com.example.notesapp_roomdatabase.view_models

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.notesapp_roomdatabase.room.Note
import com.example.notesapp_roomdatabase.room.NoteDatabase

class MainActivityViewModel(application: Application):AndroidViewModel(application) {

    lateinit var notesList:LiveData<List<Note>>

    init {
        notesList= NoteDatabase.getDb(application).noteDao().getAllNotes()
    }
}