package com.example.noteapp.Database

import androidx.room.Entity

@Entity
data class Note( val title: String, val description: String, val date: String)
