package com.example.notesky.data.mapper

import com.example.notesky.data.local.entity.NoteEntity
import com.example.notesky.domain.model.Note

fun NoteEntity.asExternalModel(): Note = Note(
    id, title, content



)

fun Note.toEntity(): NoteEntity = NoteEntity(
    id, title, content
)