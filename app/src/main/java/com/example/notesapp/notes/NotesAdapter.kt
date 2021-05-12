package com.example.notesapp.notes


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.notesapp.R
import java.util.*
import kotlin.collections.ArrayList

class NotesAdapter() :
    RecyclerView.Adapter<NotesAdapter.NotesViewHolder>() {
    var arrList = ArrayList<Notes>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        return NotesViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_rv_notes, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return arrList.size
    }



    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {

        val currNote=arrList[position];
        holder.tvtitle.text = currNote.title
        holder.tvDesc.text = arrList[position].noteText
        holder.tvDateTime.text = arrList[position].dateTime


    }

    class NotesViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvtitle=view.findViewById<TextView>(R.id.tvTitle)
        val tvDesc=view.findViewById<TextView>(R.id.tvDesc)
        val tvDateTime=view.findViewById<TextView>(R.id.tvDesc)
    }


}