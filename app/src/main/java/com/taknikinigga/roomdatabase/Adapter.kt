package com.taknikinigga.roomdatabase

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.taknikinigga.roomdatabase.data.Notes
import kotlinx.android.synthetic.main.notes_item_layout.view.*

class Adapter(val listener: onClickItem) :
    RecyclerView.Adapter<Adapter.NotesViewHolder>() {
    val notesitem = mutableListOf<Notes>()

    inner class NotesViewHolder(itemview: View) : RecyclerView.ViewHolder(itemview)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.notes_item_layout, parent, false)
        /* view.btnDelete.setOnClickListener {
             listener.onItemClick(notesitem[view.])
         }*/
        return NotesViewHolder(view)
    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        holder.itemView.apply {
            txtNotesItem.text = notesitem[position].text
            btnDelete.setOnClickListener {
                listener.onItemClick(notesitem[position])
            }
        }
    }

    override fun getItemCount(): Int {
        return notesitem.size
    }

    fun updateList(newLists: List<Notes>) {
        notesitem.clear()
        notesitem.addAll(newLists)
        notifyDataSetChanged()
    }

}

interface onClickItem {
    fun onItemClick(notes: Notes)
}