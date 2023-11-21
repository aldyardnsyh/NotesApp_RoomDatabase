package com.example.notesapp_roomdatabase.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import java.io.Serializable
import java.time.LocalDateTime

@Entity(tableName="notes_table")
data class Note(
    @PrimaryKey(autoGenerate = true) val id:Int?=null,
    @ColumnInfo(name = "title") var titleName:String?,
    @ColumnInfo(name = "description") var noteName:String?,
    @TypeConverters(LocalDateTimeConverter::class) var dateName: LocalDateTime

):Serializable