package com.example.notesapp_roomdatabase.adapter

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.example.notesapp_roomdatabase.R
import com.example.notesapp_roomdatabase.activity.AddEditActivity
import com.example.notesapp_roomdatabase.databinding.SampleNotesBinding
import com.example.notesapp_roomdatabase.room.Note
import com.example.notesapp_roomdatabase.room.NoteDatabase

class RvAdapter(var notesList: List<Note>, var context: Context):RecyclerView.Adapter<RvAdapter.MyViewHolder>(){
    private var filteredData: List<Note> = notesList.toMutableList()

    lateinit var dialog: AlertDialog
    inner class MyViewHolder(var binding: SampleNotesBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        var binding=SampleNotesBinding.inflate(LayoutInflater.from(context),parent,false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return notesList.size
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.binding.title.text=notesList.get(position).titleName
        holder.binding.descNotes.text=notesList.get(position).noteName
        holder.binding.noteTime.text= notesList.get(position).dateName.toString()


        holder.itemView.setOnClickListener {
            val intent=Intent(context, AddEditActivity::class.java)
            intent.putExtra("NOTE_EDIT",notesList.get(position))
            context.startActivity(intent)
        }
        holder.itemView.setOnLongClickListener{
            val dialog=AlertDialog.Builder(context)
            dialog.setTitle("Delete Note")
            dialog.setMessage("klik tombol hapus untuk menghapus catatan ini atau klik tombol batal untuk keluar.")
            dialog.setIcon(R.drawable.delete_ic)
            dialog.setPositiveButton("Delete"){dialogInterface,which->
                val notesDatabase= NoteDatabase.getDb(context).noteDao().deleteSingleNote(id = notesList.get(position).id!!)
            }
            dialog.setNeutralButton("Cancel"){dialogInterface,which->
                //exit()
            }
            val dialoge=dialog.create()
            dialoge.show()
            true
        }
    }
    fun updateData(newNoteList:List<Note>){
        notesList=newNoteList
        notifyDataSetChanged()
    }
}