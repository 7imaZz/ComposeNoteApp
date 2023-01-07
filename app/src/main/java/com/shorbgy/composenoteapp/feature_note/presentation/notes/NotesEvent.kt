package com.shorbgy.composenoteapp.feature_note.presentation.notes

import com.shorbgy.composenoteapp.feature_note.domain.model.Note
import com.shorbgy.composenoteapp.feature_note.domain.util.NoteOrder

sealed class NotesEvent{
    data class Order(val noteOrder: NoteOrder): NotesEvent()
    data class DeleteNote(val note: Note): NotesEvent()
    object RestoreNote: NotesEvent()
    object ToggleOrdersSection: NotesEvent()
}
