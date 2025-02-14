package com.example.noteapp.Screens.AddNote

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import com.example.noteapp.Database.Note
import com.example.noteapp.MainActivity
import com.example.noteapp.R
import com.example.noteapp.ViewModel.NoteViewModel
import com.example.noteapp.databinding.FragmentNoteBinding

class AddNote : Fragment() {
    private lateinit var binding: FragmentNoteBinding

    private lateinit var noteViewModel: NoteViewModel
    private lateinit var noteView: View
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_note, container, false)
        binding = FragmentNoteBinding.bind(view)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        noteViewModel = (activity as MainActivity).noteViewModel
        noteView = view
    }

    private fun saveNote() {
        val title = binding.addNoteTitle.text.toString().trim()
        val body = binding.addNoteDesc.text.toString().trim()
        if (title.isNotEmpty()) {
            noteViewModel.addNote(Note(title, body))
            Toast.makeText(noteView.context,"Note saved successfully", Toast.LENGTH_SHORT).show()
            noteView.findNavController().popBackStack(R.id.homeFragment,false)

        } else {
            Toast.makeText(noteView.context,"Please enter title", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onStop() {
        super.onStop()
        saveNote()
    }
}