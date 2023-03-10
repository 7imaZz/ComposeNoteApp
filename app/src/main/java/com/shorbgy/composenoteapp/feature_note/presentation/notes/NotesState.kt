package com.shorbgy.composenoteapp.feature_note.presentation.notes

import com.shorbgy.composenoteapp.feature_note.domain.model.Note
import com.shorbgy.composenoteapp.feature_note.domain.util.NoteOrder
import com.shorbgy.composenoteapp.feature_note.domain.util.OrderType

data class NotesState(
    val notes: List<Note> = emptyList(),
    val noteOrder: NoteOrder = NoteOrder.Date(OrderType.Asc),
    val isOrderSectionVisible: Boolean = false
)
