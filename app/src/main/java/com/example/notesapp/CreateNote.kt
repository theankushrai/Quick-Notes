package com.example.notesapp

import android.icu.text.SimpleDateFormat
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.notesapp.Database.Notes
import com.example.notesapp.R
import com.example.notesapp.ViewModel.NotesViewModel
import com.example.notesapp.databinding.FragmentCreateNoteBinding
import com.example.notesapp.databinding.FragmentHomeBinding
import java.util.*

class CreateNote : Fragment() {

    lateinit var binding:FragmentCreateNoteBinding
    lateinit var viewModel:NotesViewModel
    lateinit var currentDate: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding= DataBindingUtil.inflate(inflater,R.layout.fragment_create_note,container,false)
        viewModel=NotesViewModel(requireNotNull(activity).application)

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
            saveNote()
        }
    }

    private fun saveNote() {
        when {
            binding.etNoteTitle.text.isNullOrEmpty() -> Toast.makeText(context, "Title Required", Toast.LENGTH_SHORT).show()
            binding.etNoteDesc.text.isNullOrEmpty() -> Toast.makeText(context, "Note Description must not be Empty", Toast.LENGTH_SHORT).show()
            else-> {
                viewModel.insertNote(
                    Notes(
                        binding.etNoteTitle.text.toString(),
                        binding.etNoteSubTitle.toString(),
                        currentDate,
                        binding.etNoteDesc.text.toString()
                    )
                )
                binding.etNoteTitle.setText("")
                binding.etNoteSubTitle.setText("")
                binding.etNoteDesc.setText("")

            }
        }

    }
}