package com.example.noteapp.Screens.EditNote

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.noteapp.MainActivity
import com.example.noteapp.R
import com.example.noteapp.ViewModel.NoteViewModel
import com.example.noteapp.databinding.FragmentEditBinding


class EditFragment : Fragment() {
    lateinit var binding: FragmentEditBinding
    private lateinit var editViewModel: NoteViewModel
    private lateinit var editView: View
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_edit, container, false)
        binding = FragmentEditBinding.bind(view)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //assign noteViewModel and editViewModel
        editViewModel = (activity as MainActivity).noteViewModel
        editView = view
    }


}