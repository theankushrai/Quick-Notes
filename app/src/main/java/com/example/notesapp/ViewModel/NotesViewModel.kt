package com.example.notesapp.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.notesapp.Database.NoteDao
import com.example.notesapp.Database.Notes
import com.example.notesapp.Database.NotesDatabase
import kotlinx.coroutines.launch

class NotesViewModel(application: Application):AndroidViewModel(application) {
    private val dao:NoteDao = NotesDatabase.getDatabase(application).noteDao()
    val allNotes:LiveData<List<Notes>> = dao.getAllNotes()

    fun getNote(id:Int):Notes{
        return dao.getSpecificNote(id)
    }
    fun insertNote(note:Notes){
        viewModelScope.launch {
            dao.insertNotes(note)
        }
    }
    fun deleteNote(note:Notes){
        viewModelScope.launch {
            dao.deleteNote(note)
        }
    }
    fun deleteNoteatId(id:Int){
        viewModelScope.launch {
            dao.deleteSpecificNote(id)
        }
    }
    fun updateNote(note:Notes){
        viewModelScope.launch {
            dao.updateNote(note)
        }
    }

}