package com.example.notesapp

import android.icu.text.SimpleDateFormat
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.notesapp.notes.Notes
import com.example.notesapp.databinding.FragmentCreateNoteBinding
import com.example.notesapp.notes.NotesDatabase
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch
import java.util.*

class CreateNote : Fragment() {

    lateinit var binding:FragmentCreateNoteBinding
    lateinit var currentDate: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= DataBindingUtil.inflate(inflater,R.layout.fragment_create_note,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val sdf = SimpleDateFormat("dd/M/yyyy hh:mm:ss", Locale.getDefault())
         currentDate=sdf.format(Date())
        //setting date time text as current date
        binding.tvDateTime.text=currentDate
        binding.imgBack.setOnClickListener {
            it.findNavController().navigateUp()
        }
        binding.imgDone.setOnClickListener {
            val result=saveNote()
            if(result) {
                findNavController().navigateUp()
                Snackbar.make(view, "Note Created", Snackbar.LENGTH_SHORT).show()
            }
        }
    }
    private fun saveNote():Boolean {
        when {
            binding.etNoteTitle.text.isNullOrEmpty() -> Toast.makeText(context, "Title Required", Toast.LENGTH_SHORT).show()
            binding.etNoteDesc.text.isNullOrEmpty() -> Toast.makeText(context, "Note Description must not be Empty", Toast.LENGTH_SHORT).show()
            else-> {
                val note=Notes()
                note.title=binding.etNoteTitle.text.toString()
                note.subTitle=binding.etNoteSubTitle.text.toString()
                note.dateTime=currentDate
                lifecycleScope.launch {
                    NotesDatabase.getDatabase(requireContext()).noteDao().insertNotes(note)
                }
                return true
            }
        }
        return false
    }
}