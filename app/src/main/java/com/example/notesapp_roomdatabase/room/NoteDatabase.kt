package com.example.notesapp_roomdatabase.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [Note::class], version = 1, exportSchema = false)
@TypeConverters(LocalDateTimeConverter::class)
abstract class NoteDatabase: RoomDatabase() {
    abstract fun noteDao():NoteDao

    companion object {
        public var roomDb: NoteDatabase? = null
        fun getDb(context: Context): NoteDatabase {
            if (roomDb == null) {
                roomDb = Room.databaseBuilder(context, NoteDatabase::class.java, "note_database")
                    .allowMainThreadQueries().build()
            }
            return roomDb!!
        }
    }
}