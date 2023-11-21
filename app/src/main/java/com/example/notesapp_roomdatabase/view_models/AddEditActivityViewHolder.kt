package com.example.notesapp_roomdatabase.view_models

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import com.example.notesapp_roomdatabase.room.Note
import com.example.notesapp_roomdatabase.room.NoteDatabase

class AddEditActivityViewHolder(application: Application):AndroidViewModel(application) {

    fun insert(note: Note, context: Context){
        NoteDatabase.getDb(context).noteDao().insert(note)
    }
    fun update(note: Note,context: Context){
        NoteDatabase.getDb(context).noteDao().update(note)
    }
}