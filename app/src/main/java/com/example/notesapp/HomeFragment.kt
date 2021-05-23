package com.example.notesapp

import android.os.Bundle
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.notesapp.databinding.FragmentHomeBinding
import com.example.notesapp.notes.NotesAdapter
import com.example.notesapp.notes.NotesDatabase
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var adapter:NotesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding=DataBindingUtil.inflate(inflater,R.layout.fragment_home,container,false)
        binding.fabBtnCreateNote.setOnClickListener{
            it.findNavController().navigate(R.id.action_homeFragment3_to_createNote)
        }
        adapter = NotesAdapter()

        lifecycleScope.launch {
            val notes = NotesDatabase.getDatabase(requireContext()).noteDao().getAllNotes()
            //implementing the recyclerview and adapter
            binding.recyclerView.setHasFixedSize(true)
            binding.recyclerView.layoutManager=StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)
            adapter.setData(notes)
            binding.recyclerView.adapter = adapter
        }

        val onClicked = object :NotesAdapter.OnItemClickListener{
            override fun onClicked(noteId: Int) {

                lifecycleScope.launch {
                    val note=NotesDatabase.getDatabase(requireContext()).noteDao().getSpecificNote(noteId)
                    findNavController().navigate(HomeFragmentDirections.actionHomeFragment3ToViewNote(note.title,note.subTitle,note.noteText,note.id))
                }
            }

        }
        //setting notes onclick listener
        adapter.setOnClickListener(onClicked)
            return binding.root
    }

}