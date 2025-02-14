package com.example.noteapp.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.noteapp.Database.Note
import com.example.noteapp.Database.NoteDatabase
import com.example.noteapp.Repository.NoteRepo
import kotlinx.coroutines.launch

class NoteViewModel(private val app : Application, private val repo : NoteRepo
): AndroidViewModel(app) {
    fun deleteNote(note: Note) {
        viewModelScope.launch {
            repo.delete(note)
        }
    }
    fun addNote(note: Note) {
        viewModelScope.launch {
            repo.insert(note)
        }
    }
    fun getAllNotes() =
            repo.getAllNotes()
}