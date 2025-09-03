package com.example.notesky.data.repository

import com.example.notesky.data.local.dao.NoteDao
import com.example.notesky.data.mapper.asExternalModel
import com.example.notesky.data.mapper.toEntity
import com.example.notesky.domain.model.Note
import com.example.notesky.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow
import  kotlinx.coroutines.flow.map

class NoteRepositoryImpl(
    private val dao : NoteDao
) : NoteRepository {
    override fun getAllNotes(): Flow<List<Note>> {
        return dao.getAllNotes()
            .map { notes ->
                notes.map {
                   it.asExternalModel()
                }

            }
    }

    override suspend fun getNoteById(id: Int): Note? {
        return dao.getNoteById(id)?.asExternalModel()
    }

    override suspend fun insertNote(note: Note) {
        dao.insertNote(note.toEntity())
    }

    override suspend fun deleteNote(note: Note) {
        dao.deleteNote(note.toEntity())
    }

    override suspend fun updateNote(note: Note) {
        dao.updateNote(note.toEntity())
    }

}