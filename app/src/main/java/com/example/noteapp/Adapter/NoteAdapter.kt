package com.example.noteapp.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.noteapp.Database.Note
import com.example.noteapp.databinding.NoteLayoutBinding

class NoteAdapter(private val listener: OnNoteClickListener): RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {
    interface OnNoteClickListener {
        fun onDeleteClick(note: Note)
    }

    class NoteViewHolder(binding: NoteLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
        val title = binding.noteTitle
        val content = binding.noteDesc
        val deleteButton = binding.btnDelete
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        return NoteViewHolder(NoteLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    }
    private val differCallBack = object : DiffUtil.ItemCallback<Note>() {
        override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
            return oldItem.id == newItem.id && oldItem.title == newItem.title && oldItem.description == newItem.description
        }

        override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
            return oldItem == newItem
        }


    }
    val differ = AsyncListDiffer(this, differCallBack)

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val currentNote = differ.currentList[position]
        holder.title.text = currentNote.title
        holder.content.text = currentNote.description
        holder.deleteButton.setOnClickListener {
            listener.onDeleteClick(currentNote)
        }

    }
}

