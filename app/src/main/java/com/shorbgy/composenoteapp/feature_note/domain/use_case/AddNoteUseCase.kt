package com.shorbgy.composenoteapp.feature_note.domain.use_case

import com.shorbgy.composenoteapp.feature_note.domain.model.InvalidNoteException
import com.shorbgy.composenoteapp.feature_note.domain.model.Note
import com.shorbgy.composenoteapp.feature_note.domain.repository.NoteRepository

class AddNoteUseCase
constructor(
    private val repository: NoteRepository
){

    @Throws(InvalidNoteException::class)
    suspend operator fun invoke(note: Note) {
        if (note.title.isBlank())
            throw InvalidNoteException("The title of note is required")
        if (note.content.isBlank())
            throw InvalidNoteException("The body of note is required")
        repository.insertNote(note)
    }
}