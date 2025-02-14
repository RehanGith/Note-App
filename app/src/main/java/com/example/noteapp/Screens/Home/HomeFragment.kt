package com.example.noteapp.Screens.Home

import android.app.AlertDialog
import android.app.Application
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController

import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.noteapp.Adapter.NoteAdapter
import com.example.noteapp.Database.Note
import com.example.noteapp.Database.NoteDatabase
import com.example.noteapp.MainActivity
import com.example.noteapp.R
import com.example.noteapp.Repository.NoteRepo
import com.example.noteapp.ViewModel.NoteViewModel
import com.example.noteapp.ViewModel.ViewModelFactory
import com.example.noteapp.databinding.FragmentHomeBinding

class HomeFragment : Fragment(), NoteAdapter.OnNoteClickListener {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var noteViewModel: NoteViewModel

    private lateinit var noteAdapter: NoteAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        binding = FragmentHomeBinding.bind(view)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        noteViewModel = (activity as MainActivity).noteViewModel
        initializeRecyclerView()
        binding.addNoteFab.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_addNote)
        }
    }

    private fun updateUi(note : List<Note>) {
        if(note.isNotEmpty()){
            binding.homeRecyclerView.visibility = View.VISIBLE
            binding.emptyNotesImage.visibility = View.GONE
        } else {
            binding.homeRecyclerView.visibility = View.GONE
            binding.emptyNotesImage.visibility = View.VISIBLE
        }
    }
    private fun initializeRecyclerView() {
        noteAdapter = NoteAdapter(this)
        binding.homeRecyclerView.apply {
            adapter = noteAdapter
            layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            setHasFixedSize(true)
        }
        activity.let {
            noteViewModel.getAllNotes().observe(viewLifecycleOwner) {note ->
                noteAdapter.differ.submitList(note)
                updateUi(note)
            }
        }

    }

    override fun onDeleteClick(note: Note) {
        showDeleteDialog(note)
    }

    override fun onItemClick(note: Note) {
        
    }

    private fun showDeleteDialog(note : Note) {
        AlertDialog.Builder(requireContext()).
                setTitle("Delete Note").
                setMessage("Are you sure you want to delete this note?").
                setPositiveButton("Yes") {dialog, _ ->
                    noteViewModel.deleteNote(note)
                    dialog.dismiss()
                }.
                setNegativeButton("No") {dialog, _ ->
                    dialog.dismiss()
                }.create().show()
    }


}