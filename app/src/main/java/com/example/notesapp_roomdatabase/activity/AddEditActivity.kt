package com.example.notesapp_roomdatabase.activity

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.notesapp_roomdatabase.MainActivity
import com.example.notesapp_roomdatabase.databinding.ActivityAddEditBinding
import com.example.notesapp_roomdatabase.room.Note
import com.example.notesapp_roomdatabase.view_models.AddEditActivityViewHolder
import java.time.LocalDateTime

class AddEditActivity : AppCompatActivity() {
    val binding by lazy {
        ActivityAddEditBinding.inflate(layoutInflater)
    }
    lateinit var addEditActivityViewHolder: AddEditActivityViewHolder

    @SuppressLint("NewApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        addEditActivityViewHolder = ViewModelProvider(this).get(AddEditActivityViewHolder::class.java)

        binding.backBtn.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
        if (intent.hasExtra("NOTE_EDIT"))
        {
            var note:Note=intent.getSerializableExtra("NOTE_EDIT") as Note

            binding.title.setText(note.titleName)
            binding.disp.setText(note.noteName)

            binding.saveNotes.setOnClickListener {
                var title = binding.title.text.toString()
                var disp = binding.disp.text.toString()
                var currentDateTime = LocalDateTime.now()

                note.titleName = title
                note.noteName = disp
                note.dateName=currentDateTime

                if (title.isNotEmpty() && disp.isNotEmpty()) {
                    addEditActivityViewHolder.update(note, this)
                    finish()
                } else {
                    Toast.makeText(this, "Please Enter Title & Description", Toast.LENGTH_SHORT).show()
                }
            }


        }else {
            binding.saveNotes.setOnClickListener {
                var title = binding.title.text.toString()
                var disp = binding.disp.text.toString()
                var currentDateTime = LocalDateTime.now()

                if (title.isNotEmpty() && disp.isNotEmpty()) {
                    val note = Note(titleName = title, noteName = disp, dateName = currentDateTime)
                    addEditActivityViewHolder.insert(note, this)
                    finish()
                } else {
                    Toast.makeText(this, "Please Enter Title & Description", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }
    }
}