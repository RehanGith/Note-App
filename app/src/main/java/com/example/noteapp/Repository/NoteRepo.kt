package com.example.noteapp.Repository

import com.example.noteapp.Database.Note
import com.example.noteapp.Database.NoteDatabase

class NoteRepo(val db: NoteDatabase) {
    suspend fun insert(item: Note) = db.getNoteDao().insertNote(item)
    suspend fun delete(item: Note) = db.getNoteDao().deleteNote(item)
    suspend fun update(item: Note) = db.getNoteDao().updateNote(item)

    suspend fun getAllNotes() = db.getNoteDao().getAllNotes()
}


