package com.example.notesapp_roomdatabase.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface NoteDao{
    @Query("SELECT * FROM notes_table ORDER BY id DESC")
    fun getAllNotes():LiveData<List<Note>>


    @Query("SELECT * FROM notes_table WHERE id=:id")
    fun getSpecificNote(id:Int):Note

    @Query("DELETE FROM notes_table WHERE id=:id")
    fun deleteSingleNote(id: Int): Int

    @Query("DELETE FROM notes_table")
    fun deleteAllNotes()

    @Update
    fun update(note: Note)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(note: Note)
}