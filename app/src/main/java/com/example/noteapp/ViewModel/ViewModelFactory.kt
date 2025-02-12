package com.example.noteapp.ViewModel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.noteapp.Repository.NoteRepo

class ViewModelFactory(private val app: Application, private val repo: NoteRepo): ViewModelProvider.Factory  {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return NoteViewModel(app, repo) as T
    }
}