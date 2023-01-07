package com.shorbgy.composenoteapp.feature_note.domain.use_case

import com.shorbgy.composenoteapp.feature_note.domain.model.Note
import com.shorbgy.composenoteapp.feature_note.domain.repository.NoteRepository
import com.shorbgy.composenoteapp.feature_note.domain.util.NoteOrder
import com.shorbgy.composenoteapp.feature_note.domain.util.OrderType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetNotesUseCase(
    private val repository: NoteRepository
) {

    operator fun invoke(
        noteOrder: NoteOrder = NoteOrder.Date(OrderType.Asc)
    ): Flow<List<Note>> {
        return repository.getAllNotes().map {
            when(noteOrder.orderType){
                is OrderType.Asc->{
                    when(noteOrder){
                        is NoteOrder.Title -> it.sortedBy { order -> order.title.lowercase() }
                        is NoteOrder.Date -> it.sortedBy { order -> order.timeStamp }
                        is NoteOrder.Color -> it.sortedBy { order -> order.color }
                    }
                }
                is OrderType.Desc->{
                    when(noteOrder){
                        is NoteOrder.Title -> it.sortedByDescending { order -> order.title.lowercase() }
                        is NoteOrder.Date -> it.sortedByDescending { order -> order.timeStamp }
                        is NoteOrder.Color -> it.sortedByDescending { order -> order.color }
                    }
                }
            }
        }
    }
}