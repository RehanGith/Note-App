package com.example.noteapp

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProvider
import com.example.noteapp.Database.NoteDatabase
import com.example.noteapp.Repository.NoteRepo
import com.example.noteapp.Screens.Home.HomeFragment
import com.example.noteapp.ViewModel.NoteViewModel
import com.example.noteapp.ViewModel.ViewModelFactory
import com.example.noteapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    lateinit var noteViewModel: NoteViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpViewModel()
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragmentContainerView, HomeFragment())
        transaction.commit()
    }
    private fun setUpViewModel() {
        val repo = NoteRepo(NoteDatabase(this))
        val viewModelFactory = ViewModelFactory(application, repo)
        noteViewModel = ViewModelProvider(this, viewModelFactory)[NoteViewModel::class.java]
    }

}