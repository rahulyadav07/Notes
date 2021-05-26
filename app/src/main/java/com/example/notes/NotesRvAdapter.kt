package com.example.notes

import android.content.Context
import android.view.LayoutInflater
import android.view.OrientationEventListener
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NotesRvAdapter( private val context: Context,val listener: INotesRVAdapter) : RecyclerView.Adapter<NotesRvAdapter.NoteViewHolder>() {
    val allnotes = ArrayList<Note>()
    inner class NoteViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val textView = itemView.findViewById<TextView>(R.id.text)
        val deletebutton = itemView.findViewById<ImageView>(R.id.deleteButton)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val viewHolder =NoteViewHolder(LayoutInflater.from(context).inflate(R.layout.item_note,parent,false))
        viewHolder.deletebutton.setOnClickListener{
            listener.onItemClicked(allnotes[viewHolder.adapterPosition])
        }
        return viewHolder
    }


    override fun getItemCount(): Int {
        return allnotes.size
    }
    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val currentNote = allnotes[position]
        holder.textView.text = currentNote.text
    }
    fun updateList(newlist:ArrayList<Note>){
        allnotes.clear()
        allnotes.addAll(newlist)
        notifyDataSetChanged()
    }
}
interface INotesRVAdapter{
    fun onItemClicked(note:Note)
}