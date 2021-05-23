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
import com.example.notesapp.databinding.FragmentViewNoteBinding
import com.example.notesapp.notes.Notes
import com.example.notesapp.notes.NotesAdapter
import com.example.notesapp.notes.NotesDatabase
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*

class ViewNote : Fragment() {
    lateinit var binding:FragmentViewNoteBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding=DataBindingUtil.inflate(inflater,R.layout.fragment_view_note,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val args=ViewNoteArgs.fromBundle(requireArguments())
        binding.etNoteTitle.setText(args.title)
        binding.etNoteSubTitle.setText(args.subtitle)
        binding.etNoteDesc.setText(args.description)

        binding.imgBack.setOnClickListener {
            it.findNavController().navigateUp()
        }
        binding.imgDone.setOnClickListener {
//            val result=updateNote(args)
//            if(result) {
//                requireActivity().supportFragmentManager.popBackStack()
//                Snackbar.make(view, "Note Updated", Snackbar.LENGTH_SHORT).show()
//            }
            it.findNavController().navigateUp()
        }
//        binding.delbutton.setOnClickListener {

//            lifecycleScope.launch {
//                val note: Notes =NotesDatabase.getDatabase(requireActivity()).noteDao().getSpecificNote(args.id)
//                NotesDatabase.getDatabase(requireActivity()).noteDao().deleteNote(note)
//                withContext(Dispatchers.Main){
//                    Snackbar.make(it,"Note Deleted!!",Snackbar.LENGTH_SHORT).show()
//                }
//            }
//            it.findNavController().navigateUp()

//        }
    }
//    private fun updateNote(args: ViewNoteArgs):Boolean {
//        when {
//            binding.etNoteTitle.text.isNullOrEmpty() -> Toast.makeText(context, "Title Required", Toast.LENGTH_SHORT).show()
//            binding.etNoteDesc.text.isNullOrEmpty() -> Toast.makeText(context, "Note Description must not be Empty", Toast.LENGTH_SHORT).show()
//            else-> {
//
//                val currentDate=SimpleDateFormat("dd/M/yyyy hh:mm:ss", Locale.getDefault()).format(Date())
//
//                val note=Notes()
//                note.id=args.id
//                note.title=binding.etNoteTitle.text.toString()
//                note.subTitle=binding.etNoteSubTitle.text.toString()
//                note.dateTime=currentDate
//                lifecycleScope.launch {
//                    NotesDatabase.getDatabase(requireContext()).noteDao().insertNotes(note)
//                }
//
//                return true
//            }
//        }
//        return false
//
//    }
}