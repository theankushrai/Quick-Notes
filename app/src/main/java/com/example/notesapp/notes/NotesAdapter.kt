package com.example.notesapp.notes


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.EditText
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.notesapp.R
import java.util.*
import kotlin.collections.ArrayList

class NotesAdapter() :
    RecyclerView.Adapter<NotesAdapter.NotesViewHolder>() {

    var listener: OnItemClickListener? = null
    var arrList = ArrayList<Notes>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        return NotesViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_rv_notes, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return arrList.size
    }

    fun setData(arrNotesList: List<Notes>){
        arrList = arrNotesList as ArrayList<Notes>
    }


    fun setOnClickListener(listener1: OnItemClickListener){
        listener = listener1
    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {

        val currNote=arrList[position];
        holder.tvtitle.text = currNote.title
        holder.tvDesc.text = arrList[position].noteText
        holder.tvDateTime.text = arrList[position].dateTime

        //setting on click listenet

        holder.cardView.setOnClickListener {
            listener!!.onClicked(arrList[position].id)
        }



    }

    class NotesViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvtitle=view.findViewById<TextView>(R.id.tvTitle)
        val tvDesc=view.findViewById<TextView>(R.id.tvDesc)
        val tvDateTime=view.findViewById<TextView>(R.id.tvDesc)
        val cardView=view.findViewById<CardView>(R.id.cardView)
    }

    interface OnItemClickListener{
        fun onClicked(noteId:Int)
    }


}