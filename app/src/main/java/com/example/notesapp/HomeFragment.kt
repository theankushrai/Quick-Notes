package com.example.notesapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.notesapp.databinding.FragmentHomeBinding
import com.example.notesapp.notes.Notes
import com.example.notesapp.notes.NotesAdapter
import com.example.notesapp.notes.NotesDatabase
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding=DataBindingUtil.inflate(inflater,R.layout.fragment_home,container,false)

        binding.fabBtnCreateNote.setOnClickListener{
            it.findNavController().navigate(R.id.action_homeFragment3_to_createNote)
        }

        lifecycleScope.launch {
            //implementing the recyclerview and adapter
            binding.recyclerView.setHasFixedSize(true)
            binding.recyclerView.layoutManager=StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)
            val adapter = NotesAdapter()

            adapter.arrList= NotesDatabase.getDatabase(requireContext()).noteDao().getAllNotes() as ArrayList<Notes>
            binding.recyclerView.adapter = adapter

        }

        return binding.root
    }

}