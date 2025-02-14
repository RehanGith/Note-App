package com.example.noteapp.Screens.EditNote

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ShareCompat
import androidx.navigation.fragment.findNavController
import com.example.noteapp.Database.Note
import com.example.noteapp.MainActivity
import com.example.noteapp.R
import com.example.noteapp.ViewModel.NoteViewModel
import com.example.noteapp.databinding.FragmentEditBinding


class EditFragment : Fragment() {
    lateinit var binding: FragmentEditBinding
    private lateinit var editViewModel: NoteViewModel
    private lateinit var editView: View
    private var note : Note? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_edit, container, false)
        binding = FragmentEditBinding.bind(view)
        setHasOptionsMenu(true)
        return view
    }

    private fun shareCompact() : Intent {
        val shareIntent = ShareCompat.IntentBuilder.from(requireActivity())
            .setType("text/plain")
            .setChooserTitle("Share Note")
            .setText("Title: ${note?.title}\n\nContent: ${note?.description}")
            .intent
        return Intent.createChooser(shareIntent, null)
    }
    private fun shareNote(){
        startActivity(shareCompact())
    }

    @Deprecated("Deprecated in Java")
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.share, menu)
    }
    @Deprecated("Deprecated in Java")
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.share -> shareNote()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        note = arguments?.getParcelable("note")
        //assign noteViewModel and editViewModel
        editViewModel = (activity as MainActivity).noteViewModel
        editView = view
        note ?.let {note->
            binding.editNoteTitle.setText(note.title)
            binding.editNoteDesc.setText(note.description)
        } ?: kotlin.run {
            Toast.makeText(context, "Note is null", Toast.LENGTH_SHORT).show()
            findNavController().navigateUp()
        }

    }
    private fun updateNote(){
        val title = binding.editNoteTitle.text.toString()
        val description = binding.editNoteDesc.text.toString()
        if (title == note?.title.toString() && description == note?.description.toString()) {
            return
        } else {
            note?.title = title
            note?.description = description
            note?.let { editViewModel.addNote(it) }

        }
    }

    override fun onPause() {
        super.onPause()
        updateNote()
    }



}