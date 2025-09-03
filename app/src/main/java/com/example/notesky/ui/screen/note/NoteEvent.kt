package com.example.notesky.ui.screen.note

sealed interface NoteEvent {
    data class TitleChange(val Value: String) : NoteEvent
    data class ContentChange  (val Value: String) : NoteEvent
    object Save : NoteEvent
    object NavigateBack : NoteEvent
    object Delete : NoteEvent
}